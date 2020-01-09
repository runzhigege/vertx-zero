package io.vertx.up.unity;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.optic.component.Dictionary;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.commune.config.DictEpsilon;
import io.vertx.up.commune.config.DualItem;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * DictSource
 * Dict
 * DictEpsilon
 */
class DictTool {

    private static final ConcurrentMap<Integer, Dictionary> POOL_DICT =
            new ConcurrentHashMap<>();

    static DualItem toDual(final JsonArray dataArray, final DictEpsilon epsilon) {
        DualItem dualMapping = null;
        if (Objects.nonNull(dataArray) && !dataArray.isEmpty()) {
            /*
             * JsonArray iteration
             */
            final JsonObject dataItem = new JsonObject();
            Ut.itJArray(dataArray).forEach(item -> {
                /*
                 * Build data in - out
                 */
                final String inValue = item.getString(epsilon.getIn());
                final String outValue = item.getString(epsilon.getOut());
                if (Ut.notNil(inValue) && Ut.notNil(outValue)) {
                    dataItem.put(inValue, outValue);
                }
            });
            dualMapping = new DualItem(dataItem);
        }
        return dualMapping;
    }

    static ConcurrentMap<String, DictEpsilon> mapEpsilon(final JsonObject epsilonJson) {
        final ConcurrentMap<String, DictEpsilon> epsilonMap = new ConcurrentHashMap<>();
        if (Ut.notNil(epsilonJson)) {
            epsilonJson.fieldNames().stream()
                    .filter(field -> epsilonJson.getValue(field) instanceof JsonObject)
                    .forEach(field -> {
                        final JsonObject fieldData = epsilonJson.getJsonObject(field);
                        final DictEpsilon epsilon = new DictEpsilon();
                        epsilon.fromJson(fieldData);
                        epsilonMap.put(field, epsilon);
                    });
        }
        return epsilonMap;
    }

    static ConcurrentMap<String, DualItem> mapDual(final ConcurrentMap<String, DictEpsilon> source,
                                                   final ConcurrentMap<String, JsonArray> dictMap) {
        /*
         * dictMap iteration
         */
        final ConcurrentMap<String, DualItem> dictData = new ConcurrentHashMap<>();
        source.forEach((field, epsilon) -> {
            /*
             * Build epsilon
             */
            if (epsilon.isValid()) {
                /*
                 * Read the data result
                 */
                final JsonArray dataArray = dictMap.get(epsilon.getSource());
                /*
                 *
                 * field = Diode
                 */
                final DualItem dualMapping = toDual(dataArray, epsilon);
                if (Objects.nonNull(dualMapping)) {
                    /*
                     * process field
                     */
                    dictData.put(field, dualMapping);
                }
            }
        });
        return dictData;
    }

    static Future<JsonArray> runTo(final JsonArray data,
                                   final ConcurrentMap<String, DualItem> mapping) {
        final JsonArray normalized = new JsonArray();
        Ut.itJArray(data).map(item -> translateTo(item, mapping)).forEach(normalized::add);
        return Ux.future(normalized);
    }

    static Future<JsonArray> runFrom(final JsonArray data,
                                     final ConcurrentMap<String, DualItem> mapping) {
        final JsonArray normalized = new JsonArray();
        Ut.itJArray(data).map(item -> translateFrom(item, mapping)).forEach(normalized::add);
        return Ux.future(normalized);
    }

    static JsonObject translateTo(final JsonObject data,
                                  final ConcurrentMap<String, DualItem> mapping) {
        if (Ut.isNil(data)) {
            return new JsonObject();
        } else {
            final JsonObject normalized = data.copy();
            mapping.keySet().stream().filter(data::containsKey)
                    .forEach(field -> {
                        /*
                         * Dual
                         */
                        final DualItem item = mapping.get(field);
                        final String value = data.getString(field);
                        /*
                         * To
                         */
                        final String result = item.from(value);
                        normalized.put(field, result);
                    });
            return normalized;
        }
    }

    static JsonObject translateFrom(final JsonObject data,
                                    final ConcurrentMap<String, DualItem> mapping) {
        if (Ut.isNil(data)) {
            return new JsonObject();
        } else {
            final JsonObject normalized = data.copy();
            mapping.keySet().stream().filter(data::containsKey)
                    .forEach(field -> {
                        /*
                         * Dual
                         */
                        final DualItem item = mapping.get(field);
                        final String value = data.getString(field);
                        /*
                         * To
                         */
                        final String result = item.to(value);
                        normalized.put(field, result);
                    });
            return normalized;
        }
    }

    static Future<ConcurrentMap<String, JsonArray>> dictCalc(final Dict dict, final MultiMap paramMap) {
        if (Objects.isNull(dict)) {
            /*
             * Not `Dict` configured
             */
            return To.toFuture(new ConcurrentHashMap<>());
        } else {
            /*
             * Dict extract here
             */
            final ConcurrentMap<String, JsonArray> dictData = new ConcurrentHashMap<>();
            if (dict.valid()) {
                /*
                 * Component Extracted
                 */
                final Class<?> dictCls = dict.getComponent();
                if (Ut.isImplement(dictCls, Dictionary.class)) {
                    /*
                     * JtDict instance for fetchAsync
                     */
                    final Dictionary dictStub = Fn.pool(POOL_DICT, dict.hashCode(),
                            () -> Ut.instance(dictCls));
                    /*
                     * Param Map / List<Source>
                     */
                    return dictStub.fetchAsync(paramMap, dict.getSource());
                } else return To.toFuture(dictData);
            }
            return To.toFuture(dictData);
        }
    }
}
