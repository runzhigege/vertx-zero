package io.vertx.tp.ambient.refine;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.eon.Strings;
import io.vertx.up.eon.Values;
import io.vertx.up.epic.Ut;

class AtQuery {

    static JsonObject filters(final String appId, final JsonArray types, final String code) {
        final JsonObject filters = new JsonObject();
        filters.put(KeField.APP_ID, appId);
        if (Values.ONE == types.size()) {
            final String firstArg = types.getString(Values.IDX);
            filters.put(KeField.TYPE, firstArg);
            /* Conflict to multi types */
            if (Ut.notNil(code)) {
                filters.put(KeField.CODE, code);
            }
        } else {
            filters.put(KeField.TYPE + ",i", types);
        }
        return filters.put(Strings.EMPTY, Boolean.TRUE);
    }
}
