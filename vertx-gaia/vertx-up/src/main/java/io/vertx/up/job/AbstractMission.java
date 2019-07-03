package io.vertx.up.job;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.store.JobPin;
import io.vertx.up.job.store.JobStore;

public abstract class AbstractMission {
    @Contract
    private transient Vertx vertx;
    @Contract
    private transient Mission mission;

    private final transient JobStore store = JobPin.getStore();

    protected Vertx vertx() {
        return this.vertx;
    }

    protected Mission mission() {
        return this.mission;
    }

    protected JobStore store() {
        return this.store;
    }
}
