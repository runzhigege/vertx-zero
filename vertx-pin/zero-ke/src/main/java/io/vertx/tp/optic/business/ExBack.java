package io.vertx.tp.optic.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * Back to UCDMB
 */
public interface ExBack {

    Future<JsonObject> writeAsync(String appId, String identifier, JsonObject record);
}
