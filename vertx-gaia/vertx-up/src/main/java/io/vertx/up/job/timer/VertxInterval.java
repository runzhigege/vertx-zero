package io.vertx.up.job.timer;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Contract;
import io.zero.epic.fn.Actuator;

public class VertxInterval implements Interval {
    @Contract
    private transient Vertx vertx;

    @Override
    public void startAt(final long delay, final long duration, final Actuator actuator) {

    }

    @Override
    public void startAt(final long duration, final Actuator actuator) {

    }

    @Override
    public void startAt(final Actuator actuator) {

    }
}
