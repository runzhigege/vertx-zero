package io.vertx.tp.plugin.job;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.TpClient;

public interface JobClient extends TpClient<JobClient> {

    static JobClient createShared(final Vertx vertx, final JsonObject config) {
        return new JobClientImpl(vertx, config);
    }

    @Fluent
    @Override
    JobClient init(JsonObject params);
}
