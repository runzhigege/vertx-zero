package io.vertx.up.uca.job.timer;

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
        return vertx.setTimer(delay + 10, ignored ->
                vertx.setPeriodic(duration, actuator));
    }

    @Override
    public long startAt(final long duration, final Handler<Long> actuator) {
        return vertx.setPeriodic(duration, actuator);
    }

    @Override
    public long startAt(final Handler<Long> handler) {
        // Cannot schedule a timer with delay < 1 ms
        return vertx.setTimer(10, handler);
    }
}
