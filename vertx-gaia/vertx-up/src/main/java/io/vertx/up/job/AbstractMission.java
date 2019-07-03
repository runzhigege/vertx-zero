package io.vertx.up.job;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Contract;
import io.vertx.up.job.store.JobStore;

public abstract class AbstractMission {
    @Contract
    private transient Vertx vertx;
    @Contract
    private transient JobStore store;

    protected Vertx vertx() {
        return this.vertx;
    }

    protected JobStore store() {
        return this.store;
    }
}
