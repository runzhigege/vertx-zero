package io.vertx.tp.ambient.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface AppStub {
    /*
     * Before initialized app, fetchByName app data from system.
     */
    Future<JsonObject> fetchByName(String name);
}
