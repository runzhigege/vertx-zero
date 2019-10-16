package io.vertx.up.uca.job;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.uca.job.plugin.JobOutcome;

/*
 * Abstract Outcome for usage of output
 */
public abstract class AbstractOutcome implements JobOutcome {
    @Contract
    private transient Vertx vertx;

    @Contract
    private transient Mission mission;

    protected Vertx vertx() {
        return this.vertx;
    }

    protected Mission mission() {
        return this.mission;
    }
}
