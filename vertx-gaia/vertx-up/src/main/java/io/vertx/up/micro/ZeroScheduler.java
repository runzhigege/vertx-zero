package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.up.annotations.Worker;
import io.vertx.zero.eon.Values;

/**
 * Background worker of Zero framework, it's for schedule of background tasks here.
 * This scheduler is for task deployment, it should deploy all tasks
 * This worker must be SINGLE ( instances = 1 ) because multi worker with the same tasks may be
 * conflicts
 */
@Worker(instances = Values.SINGLE)
public class ZeroScheduler extends AbstractVerticle {

    @Override
    public void start() {
        this.vertx.setPeriodic(2000, (item) -> {
            System.out.println("2" + Thread.currentThread().getName());
        });
        this.vertx.setPeriodic(3000, (item) -> {
            System.out.println("3" + Thread.currentThread().getName());
        });
    }
}
