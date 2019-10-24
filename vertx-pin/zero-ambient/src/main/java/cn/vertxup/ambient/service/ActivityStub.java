package cn.vertxup.ambient.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface ActivityStub {
    /*
     * Activity & Activity Change
     */
    Future<JsonObject> fetchActivity(String id);
}
