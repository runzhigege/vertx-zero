package io.vertx.up.uca.job.center;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.tp.plugin.job.JobPool;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.JobStatus;

/**
 * Start one time
 */
class OnceAgha extends AbstractAgha {

    @Override
    public Future<Long> begin(final Mission mission) {
        /*
         * 1. Execute this mission directly
         * -  This category could not be started when worker deployed, instead, this Agha should
         *    check whether this task is in `JobPool` because it will be triggered in future.
         * 2. This task is only once execution, invoke `@On / @Off` at the same time.
         * 3. This kind fo task must be triggered, could not be in plan here. It's not needed to call
         *    Interval to process task.
         * */
        /*
         * STARTING -> READY
         * Add additional code logical to set Mission status to `STOPPED`
         * instead of other status for future usage, this kind of job must be triggered by manual
         * It means that all the job of `ONCE` won't run when container started
         */
        this.moveOn(mission, true);
        mission.setStatus(JobStatus.STOPPED);

        final Promise<Long> promise = Promise.promise();
        final long jobId = this.interval().startAt((timeId) -> this.working(mission, () -> {
            /*
             * Complete future and returned Async
             */
            promise.tryComplete(timeId);
            /*
             * RUNNING -> STOPPED
             */
            this.moveOn(mission, true);
        }));
        JobPool.mount(jobId, mission.getCode());
        this.getLogger().info(Info.JOB_INTERVAL, mission.getCode(),
                String.valueOf(0), String.valueOf(-1), String.valueOf(jobId));
        return promise.future();
    }
}
