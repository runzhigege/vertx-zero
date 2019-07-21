package io.vertx.tp.optic;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface ApeakForm {
    /*
     * Read form configuration by `uri`
     * The uri is front-end address
     */
    Future<JsonObject> readByUri(String uri);
}
