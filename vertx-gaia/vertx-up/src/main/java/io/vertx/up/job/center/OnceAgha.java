package io.vertx.up.job.center;

import io.vertx.up.atom.worker.Mission;

/**
 * Start one time
 */
class OnceAgha extends AbstractAgha {

    @Override
    public boolean start(final Mission mission) {
        /*
         * 1. Execute this mission directly
         * -  This category could not be started when worker deployed, instead, this Agha should
         *    check whether this task is in `JobPool` because it will be triggered in future.
         * 2. This task is only once execution, invoke `@On / @Off` at the same time.
         * 3. This kind fo task must be triggered, could not be in plan here. It's not needed to call
         *    Interval to process task.
         * */
        this.interval().startAt(() -> {
            System.out.println("Hello");
        });
        return Boolean.TRUE;
    }
}
