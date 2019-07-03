package io.vertx.up.job.center;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Contract;
import io.vertx.up.job.store.JobConfig;
import io.vertx.up.job.store.JobPin;
import io.vertx.up.job.timer.Interval;
import io.zero.epic.Ut;

public abstract class AbstractAgha implements Agha {

    private static final JobConfig CONFIG = JobPin.getConfig();

    @Contract
    private transient Vertx vertx;

    protected Interval interval() {
        final Class<?> intervalCls = CONFIG.getInterval().getComponent();
        final Interval interval = Ut.singleton(intervalCls);
        Ut.contract(interval, Vertx.class, this.vertx);
        return interval;
    }
}
