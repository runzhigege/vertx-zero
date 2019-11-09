package io.vertx.up.commune;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.config.DualMapping;
import io.vertx.up.eon.em.MappingMode;
import io.vertx.up.util.Ut;

import java.util.Objects;

/*
 * Package scope for `DualMapping` processing
 * 1) ActIn ( BEFORE )
 * 2) ActOut ( AFTER )
 */
class ActMapper {
    /*
     * ActIn code logical here
     */
    static Record getRecord(final Object input, final Record definition, final DualMapping mapping) {
        final Record record = definition.createNew();
        if (input instanceof String) {
            final String key = (String) input;
            record.key(key);
        } else if (input instanceof JsonObject) {
            final JsonObject dataRef = (JsonObject) input;
            if (Ut.notNil(dataRef)) {
                /*
                 * Set current data to `Record` with `DualMapping`
                 * Check whether mount dual mapping first here
                 */
                final boolean isMount = isBefore(mapping);
                /*
                 * Iterator
                 */
                dataRef.fieldNames().forEach(field -> {
                    final String hitField;
                    if (isMount) {
                        /*
                         * Mount DualMapping
                         */
                        final String toField = mapping.to(field);
                        hitField = Ut.notNil(toField) ? toField : field;
                    } else {
                        /*
                         * Without DualMapping
                         */
                        hitField = field;
                    }
                    record.set(hitField, dataRef.getValue(field));
                });
            }
        }
        return record;
    }

    static Envelop getOut(final Envelop envelop, final DualMapping mapping) {
        final Object response = envelop.data();
        if (response instanceof JsonObject || response instanceof JsonArray) {
            if (isAfter(mapping)) {
                final HttpStatusCode status = envelop.status();
                if (response instanceof JsonObject) {
                    /*
                     * JsonObject here for mapping
                     */
                    final JsonObject normalized = getOut(((JsonObject) response), mapping);
                    return Envelop.success(normalized, status);
                } else {
                    /*
                     * JsonArray here for mapping
                     */
                    final JsonArray normalized = new JsonArray();
                    Ut.itJArray((JsonArray) response).map(item -> getOut(item, mapping))
                            .forEach(normalized::add);
                    return Envelop.success(normalized, status);
                }
            } else return envelop;
        } else return envelop;
    }

    private static JsonObject getOut(final JsonObject data, final DualMapping mapping) {
        final JsonObject normalized = new JsonObject();
        data.fieldNames().forEach(field -> {
            final String hitField = mapping.from(field);
            if (Ut.isNil(hitField)) {
                normalized.put(field, data.getValue(field));
            } else {
                normalized.put(hitField, data.getValue(field));
            }
        });
        return normalized;
    }

    private static boolean isBefore(final DualMapping mapping) {
        if (Objects.isNull(mapping)) {
            return false;
        }
        if (MappingMode.BEFORE != mapping.getMode() && MappingMode.AROUND != mapping.getMode()) {
            return false;
        }
        return mapping.valid();
    }


    private static boolean isAfter(final DualMapping mapping) {
        if (Objects.isNull(mapping)) {
            return false;
        }
        if (MappingMode.AFTER != mapping.getMode() && MappingMode.AROUND != mapping.getMode()) {
            return false;
        }
        return mapping.valid();
    }
}
