package io.vertx.tp.ui.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

public interface ControlStub {
    /*
     * Fetch controls by pageId
     */
    Future<JsonArray> fetchControls(String pageId);
}
