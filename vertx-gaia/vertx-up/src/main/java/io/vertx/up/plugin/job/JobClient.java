package io.vertx.up.plugin.job;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public interface JobClient {
    /*
     * Create local session store bind data
     */
    static JobClient createShared(final Vertx vertx, final JsonObject config) {
        return new JobClientImpl(vertx, config);
    }

    static JobClient createShared(final Vertx vertx) {
        return new JobClientImpl(vertx, new JsonObject());
    }

    @Fluent
    JobClient start(final String name, final Handler<AsyncResult<Boolean>> handler);
}
