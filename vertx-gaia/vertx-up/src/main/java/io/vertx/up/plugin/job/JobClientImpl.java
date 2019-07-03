package io.vertx.up.plugin.job;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class JobClientImpl implements JobClient {

    JobClientImpl(final Vertx vertx, final JsonObject config) {

    }

    @Override
    public JobClient start(final String name, final Handler<AsyncResult<Boolean>> handler) {

        return this;
    }
}
