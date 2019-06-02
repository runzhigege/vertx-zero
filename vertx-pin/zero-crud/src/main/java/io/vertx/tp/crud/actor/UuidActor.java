package io.vertx.tp.crud.actor;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxField;
import io.zero.epic.Ut;

import java.util.UUID;

class UuidActor extends AbstractActor {

    @Override
    public JsonObject proc(final JsonObject data, final IxConfig config) {
        final IxField field = config.getField();
        /* Primary Key Add */
        final String keyField = field.getKey();
        if (Ut.notNil(keyField)) {
            /* Value Extract */
            final String keyValue = data.getString(keyField);
            if (Ut.isNil(keyValue)) {
                /* Primary Key */
                data.put(keyField, UUID.randomUUID().toString());
            }
        }
        return data;
    }
}
