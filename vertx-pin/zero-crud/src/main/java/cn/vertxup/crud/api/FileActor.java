package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.cv.IxMsg;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.optic.component.Dictionary;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.tp.plugin.excel.atom.ExRecord;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.commune.Envelop;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.commune.config.DictEpsilon;
import io.vertx.up.commune.config.DictSource;
import io.vertx.up.commune.config.DualItem;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxJooq;
import io.vertx.up.util.Ut;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Queue
public class FileActor {

    private static final Annal LOGGER = Annal.get(FileActor.class);

    @Plugin
    private transient ExcelClient client;

    @Address(Addr.File.IMPORT)
    public Future<Envelop> importFile(final Envelop request) {
        /* Import data here for result */
        final String actor = Ux.getString(request);
        final String filename = Ux.getString1(request);

        final Promise<Envelop> promise = Promise.promise();
        final File file = new File(filename);
        if (file.exists()) {
            /* IxConfig */
            final IxModule config = IxPin.getActor(actor);
            /* IxDao */
            final UxJooq jooq = IxPin.getDao(config, request.headers());

            Fn.safeJvm(() -> {
                /*
                 * Read file to inputStream
                 */
                final InputStream inputStream = new FileInputStream(file);
                /*
                 * Set<ExTable>
                 */
                final Set<ExTable> tables = this.client.ingest(inputStream, true);
                final Set<ExTable> filtered = tables.stream()
                        /*
                         * Filtered the tables that equal `config.getTabel`
                         */
                        .filter(Objects::nonNull)
                        .filter(item -> Objects.nonNull(item.getName()))
                        .filter(item -> item.getName().equals(config.getTable()))
                        .collect(Collectors.toSet());
                /*
                 * Record extracting and calculated
                 */
                final List<JsonObject> prepared = new ArrayList<>();
                filtered.forEach(table -> {
                    final List<JsonObject> records = table.get().stream()
                            .filter(Objects::nonNull)
                            .map(ExRecord::toJson)
                            .collect(Collectors.toList());
                    Ix.infoRest(LOGGER, "Table: {0}, Records: {1}", table.getName(), String.valueOf(records.size()));
                    prepared.addAll(records);
                });
                /*
                 * Dictionary injection for created and updated
                 */
                final Function<JsonObject, Future<JsonObject>> fnMount =
                        (input) -> this.fetchDict(config, request).compose(dictMap -> {
                            if (Objects.isNull(dictMap)) {
                                return Ux.future(input);
                            } else {
                                /*
                                 * Additional Steps
                                 */
                                final ConcurrentMap<String, DictEpsilon> epsilonMap = config.getEpsilon();
                                final ConcurrentMap<String, DualItem> dual = Ux.dictDual(epsilonMap, dictMap);
                                /*
                                 * Max calculation here for
                                 * 1) Excel self reference of group
                                 * 2) Database reference of current group
                                 */
                                dual.forEach((field, dualItem) -> {
                                    /*
                                     * Based calculation for dict configuration
                                     */
                                    final String value = input.getString(field);
                                    if (Ut.notNil(value)) {
                                        /*
                                         * Extracted
                                         */
                                        final String converted = dualItem.to(value);
                                        if (Ut.isNil(converted)) {
                                            /*
                                             * Excel, Self reference
                                             */
                                            final String found = this.fetchTo(value, prepared, epsilonMap.get(field));
                                            input.put(field, found);
                                        } else {
                                            /*
                                             * Existing, Hit database reference
                                             */
                                            input.put(field, converted);
                                        }
                                    }
                                });
                                return Ux.future(input);
                            }
                        });
                /*
                 * Apply default value
                 */
                final List<Future<JsonObject>> futures = new ArrayList<>();
                prepared.forEach(record -> {
                    /* Header, sigma, appId, appKey */
                    IxActor.header().bind(request).proc(record, config);
                    /* Serial */
                    futures.add(IxActor.serial().bind(request).procAsync(record, config)
                            /* Unique Filters */
                            .compose(normalized -> IxActor.unique().procAsync(normalized, config))
                            /* Search result */
                            .compose(filters -> Ix.search(filters, config).apply(jooq))
                            /* Result confirmed */
                            .compose(queried -> Ix.isExist(queried) ?
                                    /* Update Flow */
                                    Ix.unique(queried)
                                            /* Audit: Update */
                                            .compose(item -> IxActor.update().bind(request).procAsync(item, config))
                                            .compose(fnMount)
                                            /* Final Update */
                                            .compose(json -> Ix.entityAsync(json, config))
                                            .compose(jooq::updateAsync)
                                            .compose(Ux::fnJObject) :
                                    /* Insert UUID */
                                    IxActor.uuid().procAsync(record, config)
                                            /* Audit: Create / Update */
                                            .compose(item -> IxActor.create().bind(request).procAsync(item, config))
                                            .compose(item -> IxActor.update().bind(request).procAsync(item, config))
                                            .compose(fnMount)
                                            /* Final Insert */
                                            .compose(json -> Ix.entityAsync(json, config))
                                            .compose(jooq::insertAsync)
                                            .compose(Ux::fnJObject)
                            )
                    );
                });
                final Future<JsonArray> result = Ux.thenCombine(futures);
                result.setHandler(imported -> {
                    Ix.infoDao(LOGGER, IxMsg.FILE_LOADED, filename);
                    promise.complete(Envelop.success(Boolean.TRUE));
                });
            });
        } else {
            promise.complete(Envelop.success(Boolean.FALSE));
        }
        return promise.future();
    }

    @Address(Addr.File.EXPORT)
    public Future<Envelop> exportFile(final Envelop request) {
        /* Headers */
        final ConcurrentMap<String, String> exportedHeaders = new ConcurrentHashMap<>();
        /* Removed */
        final JsonArray removed = new JsonArray();
        /* Column sequence */
        final List<String> columnList = new ArrayList<>();
        /* Search full column and it will be used in another method */
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> Unity.fetchFull(dao, request, config)

                /* Column initialization */
                .compose(columns -> {
                    final Set<String> columnSet = new HashSet<>();
                    Ut.itJArray(columns, (column, index) -> {
                        /* Key */
                        final String columnKey = column.getString(IxPin.getColumnKey());
                        /* Name */
                        final String columnLabel = column.getString(IxPin.getColumnLabel());
                        if (Ut.notNil(columnKey) && Ut.notNil(columnLabel)) {
                            exportedHeaders.put(columnKey, columnLabel);
                            /* All columns */
                            columnSet.add(columnKey);
                        }
                    });
                    return Ux.future(columnSet);
                })

                /* Column calculation */
                .compose(columnSet -> {
                    /* Expected columns */
                    final JsonArray expected = Ux.getArray2(request);
                    columnSet.removeAll(expected.stream()
                            .filter(Objects::nonNull)
                            .map(column -> (String) column)
                            .collect(Collectors.toSet()));
                    /* Column sequence */
                    expected.stream()
                            .filter(Objects::nonNull)
                            .map(column -> (String) column)
                            .forEach(columnList::add);
                    /* projection calculation */
                    return Ux.future(Ut.toJArray(columnSet));
                })

                /* Projection calculation */
                .compose(projection -> {
                    {
                        /* Removed will be used in future */
                        removed.addAll(projection);
                    }
                    /* Parameters Extraction */
                    final JsonObject body = new JsonObject();
                    final JsonObject criteria = Ux.getJson1(request);
                    body.put(Inquiry.KEY_CRITERIA, criteria);
                    body.put(Inquiry.KEY_PROJECTION, projection);
                    /* Calculation for projection here */
                    return Ux.future(body);
                })

                /* Verify */
                .compose(input -> IxActor.verify().bind(request).procAsync(input, config))

                /* Execution */
                .compose(params -> Ix.query(params, config).apply(dao))

                /* Dict */
                .compose(response -> {
                    /* Data for ExTable */
                    JsonArray data = response.getJsonArray("list");
                    if (Objects.isNull(data)) {
                        data = new JsonArray();
                    }
                    /*
                     * To avoid final in lambda expression
                     */
                    final JsonArray inputData = data.copy();
                    return this.fetchDict(config, request).compose(dictMap -> {
                        /*
                         * Dictionary fetching for exporting
                         */
                        if (Objects.isNull(dictMap)) {
                            return Ux.future(inputData);
                        } else {
                            return Ux.dictTo(inputData, config.getEpsilon(), dictMap);
                        }
                    });
                })
                /* Data Exporting */
                .compose(data -> {
                    /* Left columns, removed useless column */
                    removed.stream().map(item -> (String) item).forEach(exportedHeaders::remove);

                    /* Combine and build data of excel */
                    return Ke.combineAsync(data, exportedHeaders, columnList);
                })

                /* Final exporting her for excel download */
                .compose(data -> {
                    final String actor = Ux.getString(request);
                    return this.client.exportTable(actor, data);
                })
                .compose(buffer -> Ux.future(Envelop.success(buffer)))
        );
    }

    private String fetchTo(final String value, final List<JsonObject> source, final DictEpsilon epsilon) {
        /*
         * in = name
         * out = key
         */
        return source.stream()
                .filter(item -> value.equals(item.getString(epsilon.getIn())))
                .map(item -> item.getString(epsilon.getOut()))
                .findAny().orElse(null);
    }

    private Future<ConcurrentMap<String, JsonArray>> fetchDict(final IxModule config, final Envelop request) {
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
}
