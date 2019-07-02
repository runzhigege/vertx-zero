package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.up.annotations.Worker;
import io.vertx.zero.eon.Values;

/*
 * Background scheduler in zero for some async tasks
 * 1）Background scheduler for some task
 * 2) This task should worker in specific mode
 */
@Worker(instances = Values.SINGLE)
public class JobPublisher extends AbstractVerticle {

    @Override
    public void start() {
        /*
         * Job
         */
        this.vertx.setPeriodic(1000, id -> {
            System.out.println("H");
        });
    }
}
