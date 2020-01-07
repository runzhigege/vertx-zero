package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.optic.Apeak;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.optic.Seeker;
import io.vertx.tp.optic.component.Dictionary;
import io.vertx.up.commune.Envelop;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.commune.config.DictEpsilon;
import io.vertx.up.commune.config.DictSource;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxJooq;
import io.vertx.up.util.Ut;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

class Unity {

    private static final Annal LOGGER = Annal.get(Unity.class);

    /*
     * Seeker for lookup target resource
     * 1. Provide uri, method to find target resource of personal
     * 2. Sigma from header
     * 3. Find impact resourcedId that will be related to view.
     */
    static Future<JsonObject> fetchView(final UxJooq dao, final Envelop request, final IxModule config) {
        /* init parameters */
        final JsonObject params = Unity.initMy(request);
        return Ke.channel(Seeker.class, JsonObject::new, seeker -> Ux.future(params)
                /* Header */
                .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                /* Fetch Impact */
                .compose(seeker.on(dao)::fetchImpact));
    }

    static Future<JsonArray> fetchFull(final UxJooq dao, final Envelop request, final IxModule config) {
        /* Get Stub */
        return Ke.channel(Apeak.class, JsonArray::new, stub -> IxActor.start()
                /* Apeak column definition here */
                .compose(input -> IxActor.apeak().bind(request).procAsync(input, config))
                /* Header */
                .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                /* Fetch Full Columns */
                .compose(stub.on(dao)::fetchFull));
    }

    static Future<ConcurrentMap<String, JsonArray>> fetchDict(final Envelop request, final IxModule config) {
        /* Epsilon */
        final ConcurrentMap<String, DictEpsilon> epsilonMap = config.getEpsilon();
        /* Channel Plugin */
        final Dictionary plugin = Pocket.lookup(Dictionary.class);
        /* Dict */
        final Dict dict = config.getSource();
        if (epsilonMap.isEmpty() || Objects.isNull(plugin) || !dict.validSource()) {
            /*
             * Direct returned
             */
            Ix.infoRest(LOGGER, "Plugin condition failure, {0}, {1}, {2}",
                    epsilonMap.isEmpty(), Objects.isNull(plugin), !dict.validSource());
            return Ux.future(new ConcurrentHashMap<>());
        } else {
            final List<DictSource> sources = dict.getSource();
            final MultiMap paramMap = MultiMap.caseInsensitiveMultiMap();
            final JsonObject headers = request.headersX();
            paramMap.add(KeField.SIGMA, headers.getString(KeField.SIGMA));
            /*
             * To avoid final in lambda expression
             */
            return plugin.fetchAsync(paramMap, sources);
        }
    }

    static boolean isMatch(final JsonObject record, final IxModule module) {
        /*
         * Get unique rule of current module
         */
        final IxField fieldConfig = module.getField();
        final JsonArray matrix = fieldConfig.getUnique();
        /*
         * Matrix may be multi group
         */
        final int size = matrix.size();
        for (int idx = 0; idx < size; idx++) {
            final JsonArray group = matrix.getJsonArray(idx);
            if (Ut.notNil(group)) {
                final Set<String> fields = new HashSet<>();
                group.stream().filter(Objects::nonNull)
                        .map(item -> (String) item)
                        .filter(Ut::notNil)
                        .forEach(fields::add);
                final boolean match = fields.stream().allMatch(field -> Objects.nonNull(record.getValue(field)));
                if (!match) {
                    Ix.warnRest(LOGGER, "Unique checking failure, check fields: `{0}`, data = {1}",
                            Ut.fromJoin(fields), record.encode());
                    return false;
                }
            }
        }
        return true;
    }

    static ConcurrentMap<String, ConcurrentMap<String, String>> dictCalc(
            final ConcurrentMap<String, JsonArray> dictMap,
            final List<JsonObject> prepared,
            final IxModule config) {
        final ConcurrentMap<String, ConcurrentMap<String, String>> resultMap
                = new ConcurrentHashMap<>();
        /*
         * 1. dictMap is the dictionary data here - EpsilonMap
         *    1) in = name
         *    2) out = key
         * Build map field = { in = out }
         *
         * Self Reference for tree importing here
         * 1) Here DictEpsilon must be `parent = true`
         * 2) When parent is true, it means that we should be capture
         * 3) Single Field: parent = true, it could be used
         *    Multi Fields: parent = true, it also could be used
         */
        final ConcurrentMap<String, DictEpsilon> epsilonMap = config.getEpsilon();
        epsilonMap.forEach((field, epsilon) -> {
            final ConcurrentMap<String, String> dictData = new ConcurrentHashMap<>();
            /*
             * Basic checking
             */
            final String source = epsilon.getSource();
            final String inField = epsilon.getIn();
            final String outField = epsilon.getOut();
            if (Ut.notNil(inField) && Ut.notNil(outField) && Ut.notNil(source)) {
                /*
                 * 1) Self Parent first
                 */
                if (epsilon.getParent()) {
                    dictData.putAll(dictCalc(prepared.stream(), inField, outField));
                }
                /*
                 * 2) Converted database source then
                 *
                 * Here are a situation, if the dict stored into database
                 * The `key` will be override in this step, it such as
                 *
                 * 2.1) Here stored
                 * Key     Name
                 * key1    Name1
                 * 2.2) When the template also contains
                 * Name
                 * Name1
                 * 2.3) Before current step, here should be
                 * Key     Name
                 * key2    Name1
                 * But now the recalculated will be
                 * Name1 = Key2 first, and then Name1 = key1 second, it means that
                 * The new generated key will be overwrite by `key1` that stored in database
                 */
                if (dictMap.containsKey(source)) {
                    final JsonArray dataArray = dictMap.get(source);
                    dictData.putAll(dictCalc(Ut.itJArray(dataArray), inField, outField));
                }
            }
            resultMap.put(field, dictData);
        });
        return resultMap;
    }

    private static ConcurrentMap<String, String> dictCalc(final Stream<JsonObject> stream,
                                                          final String in, final String out) {
        final ConcurrentMap<String, String> dictData = new ConcurrentHashMap<>();
        stream.filter(Objects::nonNull)
                .filter(json -> Objects.nonNull(json.getValue(in)))
                .filter(json -> Objects.nonNull(json.getValue(out)))
                .forEach(json -> dictData.put(
                        json.getValue(in).toString(),
                        json.getValue(out).toString()
                ));
        return dictData;
    }

    static Future<JsonObject> dictImport(final JsonObject input,
                                         final ConcurrentMap<String, ConcurrentMap<String, String>> preparedMap,
                                         final IxModule config) {
        if (Objects.isNull(preparedMap) || preparedMap.isEmpty()) {
            return Ux.future(input);
        } else {
            /*
             * Additional Steps
             */
            final ConcurrentMap<String, DictEpsilon> epsilonMap = config.getEpsilon();
            epsilonMap.keySet().forEach(field -> {
                /*
                 * Excel, Self reference
                 */
                final ConcurrentMap<String, String> prepared = preparedMap.get(field);
                final String value = input.getString(field);
                if (Objects.nonNull(prepared) && Ut.notNil(value) && !prepared.isEmpty()) {
                    final String found = prepared.get(value);
                    if (Ut.notNil(found)) {
                        input.put(field, found);
                    }
                }
            });
            return Ux.future(input);
        }
    }

    /*
     * This method is for uniform safeCall for Future<JsonArray> returned
     * It's shared by
     * /api/columns/{actor}/full
     * /api/columns/{actor}/my
     * Because all of above api returned JsonArray of columns on model
     */
/*    static <T> Future<Envelop> safeCall(final T stub, final Supplier<Future<Envelop>> executor) {
        if (Objects.isNull(stub)) {
            return Ux.future(new JsonArray()).compose(IxHttp::success200);
        } else {
            return executor.get();
        }
    }*/

    /*
     * Uri, Method instead
     * This method is only for save my columns, it provided fixed impact uri for clean cache
     * 1) Save my columns
     * 2) Clean up impact uri about cache flush
     */
    static JsonObject initMy(final Envelop envelop) {
        final String pattern = "/api/{0}/search";
        final String actor = Ux.getString(envelop);
        return new JsonObject()
                .put(KeField.URI, MessageFormat.format(pattern, actor))
                .put(KeField.METHOD, HttpMethod.POST.name());
    }
}
