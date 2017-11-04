package com.vie.ke.config;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

public interface Linker {
    /**
     * Execute the config
     *
     * @param handler
     */
    void exec(Handler<AsyncResult<JsonObject>> handler);
}
