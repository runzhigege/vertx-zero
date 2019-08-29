package io.vertx.tp.ui.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * Page Service for configuration
 */
public interface PageStub {
    /*
     * Fetch page by: app + module + page
     */
    Future<JsonObject> fetchAmp(String sigma, JsonObject params);
}
