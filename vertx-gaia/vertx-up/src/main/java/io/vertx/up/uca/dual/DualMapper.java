package io.vertx.up.uca.dual;

import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.config.DualMapping;
import io.vertx.up.util.Ut;

import java.util.Objects;

public class DualMapper implements Mapper {

    @Override
    public JsonObject in(final JsonObject in, final DualMapping mapping) {
        if (Objects.isNull(mapping)) {
            /*
             * No mapping
             */
            return in.copy();
        } else {
            /*
             * Mapping configured
             */
            final JsonObject normalized = new JsonObject();
            in.fieldNames().forEach(field -> {
                /*
                 * field is (To) field,
                 * convert to standard model attribute
                 */
                final String fromField = mapping.from(field);
                if (Ut.isNil(fromField)) {
                    normalized.put(field, in.getValue(field));
                } else {
                    normalized.put(fromField, in.getValue(field));
                }
            });
            return normalized;
        }
    }

    @Override
    public JsonObject out(final JsonObject out, final DualMapping mapping) {
        return null;
    }
}
