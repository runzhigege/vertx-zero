package io.vertx.tp.optic;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * Company Information get from system
 */
public interface EcCompany {
    /*
     * Read data by `id`
     */
    Future<JsonObject> fetchAsync(String id);
}
