package io.vertx.up.job.timer;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.up.annotations.Contract;

public class VertxInterval implements Interval {
    @Contract
    private transient Vertx vertx;

    @Override
    public long startAt(final long delay, final long duration, final Handler<Long> actuator) {
        /*
         * In this kind of situation, only predicate is ok
         * Adjust 10 ms for :
         * -- Cannot schedule a timer with delay < 1 ms
         */
        return this.vertx.setTimer(delay + 10, ignored ->
                this.vertx.setPeriodic(duration, actuator));
    }

    @Override
    public long startAt(final long duration, final Handler<Long> actuator) {
        return this.vertx.setPeriodic(duration, actuator);
    }

    @Override
    public long startAt(final Handler<Long> handler) {
        // Cannot schedule a timer with delay < 1 ms
        return this.vertx.setTimer(10, handler);
    }
}
