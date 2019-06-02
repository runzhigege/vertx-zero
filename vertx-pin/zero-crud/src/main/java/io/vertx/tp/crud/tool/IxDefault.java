package io.vertx.tp.crud.tool;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;

import java.util.UUID;

/*
 * Add Tool for normalized
 */
class IxDefault {
    /*
     * Get Add Normalized Json
     */
    static JsonObject inAdd(final Envelop envelop, final IxConfig config) {
        final IxField field = config.getField();
        final JsonObject body = Ux.getJson1(envelop);
        /* Primary Key Add */
        if (Ut.notNil(field.getKey())) {
            final String keyValue = body.getString(field.getKey());
            if (Ut.isNil(keyValue)) {
                body.put(field.getKey(), UUID.randomUUID().toString());
            }
        }
        return body;
    }

    /*
     * Get User information to set auditor
     */
}
