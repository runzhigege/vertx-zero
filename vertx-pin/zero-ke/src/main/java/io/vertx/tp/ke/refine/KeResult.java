package io.vertx.tp.ke.refine;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.cv.WebResult;
import io.vertx.up.util.Ut;

class KeResult {

    static JsonObject bool(final String key, final boolean checked) {
        final WebResult.Bool response = checked ?
                WebResult.Bool.SUCCESS : WebResult.Bool.FAILURE;
        return new JsonObject().put(key, response.name());
    }

    static Boolean bool(final JsonObject checkedJson) {
        final String result = checkedJson.getString(KeField.RESULT);
        final WebResult.Bool resultValue = Ut.toEnum(() -> result, WebResult.Bool.class, WebResult.Bool.FAILURE);
        return WebResult.Bool.SUCCESS == resultValue;
    }

    static JsonObject array(final JsonArray array) {
        return new JsonObject().put(KeField.DATA, array);
    }
}
