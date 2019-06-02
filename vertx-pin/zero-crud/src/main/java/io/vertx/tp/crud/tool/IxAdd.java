package io.vertx.tp.crud.tool;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;

import java.util.UUID;

/*
 * Add Tool for normalized
 */
class IxAdd {
    /*
     * Get Add Normalized Json
     */
    static JsonObject inAdd(final Envelop envelop, final IxConfig config) {
        final String keyField = config.getKeyField();
        final JsonObject body = Ux.getJson1(envelop);
        /* Primary Key Add */
        if (Ut.notNil(keyField)) {
            final String keyValue = body.getString(keyField);
            if (Ut.isNil(keyValue)) {
                body.put(keyField, UUID.randomUUID().toString());
            }
        }
        return body;
    }
}
