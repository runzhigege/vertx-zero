package io.vertx.tp.optic;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * User Information get from system
 */
public interface EcUser {
    /*
     * Read data by `id`
     */
    Future<JsonObject> fetchAsync(String id);
}
