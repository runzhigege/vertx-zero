package io.vertx.tp.ui.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface LayoutStub {
    /*
     * Cached layout information here to stored
     */
    Future<JsonObject> fetchLayout(String layoutId);
}
