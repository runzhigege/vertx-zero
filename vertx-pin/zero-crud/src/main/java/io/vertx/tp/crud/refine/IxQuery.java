package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.up.aiki.Ux;
import io.zero.epic.Ut;

class IxQuery {

    static Future<JsonObject> inKeys(final JsonArray array, final IxConfig config) {
        final IxField field = config.getField();
        final String keyField = field.getKey();
        /* Filters */
        final JsonObject filters = new JsonObject();
        final JsonArray keys = new JsonArray();
        Ut.itJArray(array, (item, index) -> {
            /* keyValue */
            final String keyValue = item.getString(keyField);
            if (Ut.notNil(keyValue)) {
                keys.add(keyValue);
            }
        });
        /* Filters */
        return Ux.toFuture(filters.put(keyField + ",i", keys));
    }
}
