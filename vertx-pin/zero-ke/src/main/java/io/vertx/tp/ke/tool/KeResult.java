package io.vertx.tp.ke.tool;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.WebResult;

class KeResult {

    static JsonObject bool(final String key, final boolean checked) {
        final WebResult.Bool response = checked ?
                WebResult.Bool.SUCCESS : WebResult.Bool.FAILURE;
        return new JsonObject().put(key, response.name());
    }
}
