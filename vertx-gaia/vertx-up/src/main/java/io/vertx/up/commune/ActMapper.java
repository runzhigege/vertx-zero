package io.vertx.up.commune;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.config.DualMapping;
import io.vertx.up.eon.em.ChannelType;
import io.vertx.up.eon.em.MappingMode;
import io.vertx.up.util.Ut;

import java.util.Objects;

/*
 * Package scope for `DualMapping` processing
 * 1) ActIn ( BEFORE )
 * 2) ActOut ( AFTER )
 */
class ActMapper {

    static Envelop getOut(final Envelop envelop, final DualMapping mapping, final ChannelType channelType) {
        final Object response = envelop.data();
        if (response instanceof JsonObject || response instanceof JsonArray) {
            if (isAfter(mapping)) {
                final HttpStatusCode status = envelop.status();
                if (response instanceof JsonObject) {
                    /*
                     * JsonObject here for mapping
                     */
                    final JsonObject normalized = getOut(((JsonObject) response), mapping, channelType);
                    return Envelop.success(normalized, status);
                } else {
                    /*
                     * JsonArray here for mapping
                     */
                    final JsonArray normalized = new JsonArray();
                    Ut.itJArray((JsonArray) response).map(item -> getOut(item, mapping, channelType))
                            .forEach(normalized::add);
                    return Envelop.success(normalized, status);
                }
            } else return envelop;
        } else return envelop;
    }

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
                 *
                 * Passive Only
                 */
                final boolean isBefore = isBefore(mapping);
                /*
                 * Iterator
                 */
                if (isBefore) {
                    final JsonObject normalized = dataIn(dataRef, mapping);
                    record.set(normalized);
                } else {
                    record.set(dataRef.copy());
                }
            }
        }
        return record;
    }

    private static JsonObject getOut(final JsonObject data, final DualMapping mapping, final ChannelType channelType) {
        final JsonObject normalized;
        /*
         * Mapping Structure here.
         * cmdbField -> tpField
         *
         * 1) For `ADAPTOR` / `CONNECTOR`, the mapping should be ( from -> to )
         * 2) For `ACTOR` / `DIRECTOR`, the mapping should be ( to -> from )
         */
        if (isPassive(channelType)) {
            normalized = dataOut(data, mapping);
        } else {
            normalized = dataIn(data, mapping);
        }
        return normalized;
    }

    /*
     * From ( Ox ) -> To ( Tp )
     */
    private static JsonObject dataOut(final JsonObject data, final DualMapping mapping) {
        final JsonObject normalized = new JsonObject();
        data.fieldNames().forEach(field -> {
            /*
             * field is (From) field,
             * convert to standard model attribute
             */
            final String fromField = mapping.to(field);
            if (Ut.isNil(fromField)) {
                normalized.put(field, data.getValue(field));
            } else {
                normalized.put(fromField, data.getValue(field));
            }
        });
        return normalized;
    }

    /*
     * From ( Ox ) <- To ( Tp )
     */
    private static JsonObject dataIn(final JsonObject data, final DualMapping mapping) {
        final JsonObject normalized = new JsonObject();
        data.fieldNames().forEach(field -> {
            /*
             * field is (To) field,
             * convert to standard model attribute
             */
            final String fromField = mapping.from(field);
            if (Ut.isNil(fromField)) {
                normalized.put(field, data.getValue(field));
            } else {
                normalized.put(fromField, data.getValue(field));
            }
        });
        return normalized;
    }

    /*
     * Default to passive
     */
    private static boolean isPassive(final ChannelType type) {
        if (Objects.isNull(type)) {
            return true;
        } else {
            return !(ChannelType.ACTOR == type || ChannelType.DIRECTOR == type);
        }
    }

    /*
     * Whether it's before automatic
     */
    private static boolean isBefore(final DualMapping mapping) {
        if (Objects.isNull(mapping)) {
            return false;
        }
        if (MappingMode.BEFORE != mapping.getMode() && MappingMode.AROUND != mapping.getMode()) {
            return false;
        }
        return mapping.valid();
    }

    /*
     * Whether it's after automatic
     */
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
