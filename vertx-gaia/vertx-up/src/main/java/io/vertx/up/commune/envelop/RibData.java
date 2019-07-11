package io.vertx.up.commune.envelop;


import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Constants;
import io.vertx.up.util.Ut;

import java.util.Objects;

/**
 * Data Part Only
 */
class RibData {
    /*
     * Extract Data directly
     */
    @SuppressWarnings("all")
    static <T> T get(final JsonObject data) {
        if (Objects.isNull(data) || !data.containsKey(Key.DATA)) {
            /*
             * 2 situations:
             * 1) data = null
             * 2) data without `data` key ( Zero Specification defined )
             */
            return null;
        } else {
            final Object value = data.getValue(Key.DATA);
            if (Objects.isNull(value)) {
                /*
                 * `data` key value is null.
                 */
                return null;
            } else {
                return (T) value;
            }
        }
    }

    /*
     * Extract Data by Type
     */
    static <T> T get(final JsonObject data, final Class<?> clazz) {
        T reference = null;
        if (data.containsKey(Key.DATA)) {
            final Object value = data.getValue(Key.DATA);
            reference = Rib.deserialize(value, clazz);
        }
        return reference;
    }

    static JsonObject getBody(final JsonObject data) {
        return getData(data, null);
    }

    /*
     * Extract Data by Index
     */
    static <T> T get(final JsonObject data, final Class<?> clazz, final Integer index) {
        T reference = null;
        if (data.containsKey(Key.DATA)) {
            final JsonObject raw = data.getJsonObject(Key.DATA);
            if (!Ut.isNil(raw)) {
                /* Key */
                final String key = Constants.INDEXES.get(index);
                reference = Rib.deserialize(raw.getValue(key), clazz);
            }
        }
        return reference;
    }

    static <T> void set(final JsonObject data, final String field, final T value, final Integer argIndex) {
        final JsonObject reference = getData(data, argIndex);
        if (Objects.nonNull(reference)) {
            reference.put(field, value);
        }
    }

    /*
     * Find json object ( Body Part )
     */
    private static JsonObject getData(final JsonObject data, final Integer argIndex) {
        JsonObject found = new JsonObject();
        /*
         * If the data ( JsonObject this.data ) does not contains "data",
         * Extract `data` part directly here.
         */
        final Object reference = data.getValue(Key.DATA);
        if (reference instanceof JsonObject) {
            /*
             * JsonObject = `key` = `data` part
             */
            found.mergeIn((JsonObject) reference);
        }
        /*
         * Setted data by index, it means we should capture following part in interface style
         *
         * public String method(@PathParam("name") String name,
         *                      @RequestParam("user") String user);
         * Here:
         * name argIndex = 0;
         * user argIndex = 1;
         *
         * We do not support more than 8 parameters in one method to have a good habit
         * to write readile code.
         */
        if (null == argIndex) {
            /*
             * Find the first value of type JsonObject
             */
            final JsonObject body = found.fieldNames().stream()
                    .filter(Objects::nonNull)
                    .map(found::getValue)
                    /*
                     * Predicate to test whether value is JsonObject
                     * If JsonObject, then find the first JsonObject as body
                     */
                    .filter(value -> value instanceof JsonObject)
                    .map(item -> (JsonObject) item)
                    .findFirst().orElse(null);
            if (!Ut.isNil(body)) {
                /*
                 * Move reference and let found reference set
                 */
                found = body;
            }
        } else {
            /*
             * Extract data by "argIndex", here
             * argIndex could be passed by developers or outer environment
             *
             * Here argIndex should not be null
             */
            found = found.getJsonObject(Constants.INDEXES.get(argIndex));
        }
        return found;
    }
}
