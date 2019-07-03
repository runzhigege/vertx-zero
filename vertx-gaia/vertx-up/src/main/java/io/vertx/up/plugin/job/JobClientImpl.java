package io.vertx.up.plugin.job;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.center.Agha;
import io.zero.epic.Ut;

import java.util.Objects;

public class JobClientImpl implements JobClient {

    private transient final Vertx vertx;
    private transient final JsonObject config;

    JobClientImpl(final Vertx vertx, final JsonObject config) {
        this.vertx = vertx;
        this.config = config;
    }

    @Override
    public JobClient start(final String name, final Handler<AsyncResult<Long>> handler) {
        /* Find Mission by name */
        final Mission mission = JobPool.get(name);
        if (Objects.nonNull(mission)) {
            /* Start new job here */
            final Agha agha = Agha.get(mission.getType());
            /* Bind vertx */
            Ut.contract(agha, Vertx.class, this.vertx);
            /* Start new job */
            final Long timeId = agha.begin(mission);
            /* Started */
            JobPool.start(timeId, mission.getName());
            /* Returned */
            handler.handle(Future.succeededFuture(timeId));
        }
        return this;
    }

    @Override
    public JobClient stop(final Long timerId, final Handler<AsyncResult<Boolean>> handler) {
        /* Remove mission from running pool */
        JobPool.stop(timerId);
        handler.handle(Future.succeededFuture(Boolean.TRUE));
        /* Cancel job */
        this.vertx.cancelTimer(timerId);
        return this;
    }

    @Override
    public JobClient resume(final Long timeId, final Handler<AsyncResult<Long>> handler) {
        JobPool.resume(timeId);
        /* String name get and then start */
        final String name = JobPool.name(timeId);
        return this.start(name, handler);
    }
}
