package io.vertx.up.job.timer;

import io.zero.epic.fn.Actuator;

/*
 * Scheduled for each
 */
public interface Interval {
    /**
     * Start schedule at
     *
     * @param delay    delay ms to start
     * @param duration repeat for each duration
     * @param actuator Executor
     */
    void startAt(long delay, long duration, Actuator actuator);

    /**
     * Start schedule from now without delay
     *
     * @param duration repeat for each duration
     * @param actuator Executor
     */
    void startAt(long duration, Actuator actuator);

    /**
     * Start schedule once
     *
     * @param actuator Executor
     */
    void startAt(Actuator actuator);
}
