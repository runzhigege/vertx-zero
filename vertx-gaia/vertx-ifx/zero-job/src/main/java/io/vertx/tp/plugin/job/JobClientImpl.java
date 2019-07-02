package io.vertx.tp.plugin.job;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class JobClientImpl implements JobClient {

    JobClientImpl(final Vertx vertx, final JsonObject config) {

    }

    @Override
    public JobClient init(final JsonObject config) {

        return this;
    }
}
