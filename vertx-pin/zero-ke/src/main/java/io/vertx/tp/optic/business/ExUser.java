package io.vertx.tp.optic.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * Channel for account update
 */
public interface ExUser {
    /*
     * Fetch by `modelKey` & `modelId`
     */
    Future<JsonObject> fetchRef(JsonObject filters);

    /*
     * Fetch by `modelKey` & `modelId`
     */
    Future<JsonObject> updateRef(String key, JsonObject params);
}
