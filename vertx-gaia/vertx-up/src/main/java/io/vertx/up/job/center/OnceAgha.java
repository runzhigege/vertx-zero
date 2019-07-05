package io.vertx.up.job.center;

import io.vertx.core.Future;
import io.vertx.up.atom.worker.Mission;
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
        final Future<Long> future = Future.future();
        this.interval().startAt((timeId) -> {
            if (JobStatus.STARTING == mission.getStatus()) {
                /*
                 * Preparing for job
                 **/
                this.preparing(mission);
                future.complete(timeId);
            } else if (JobStatus.READY == mission.getStatus()) {
                /*
                 * Running the job next time when current job get event
                 * from event bus trigger
                 */
                this.working(mission).compose(envelop -> {
                    /*
                     * Complete future and returned: Async
                     */
                    future.complete(timeId);
                    return Future.succeededFuture(envelop);
                });
            }
        });
        return future;
    }
}
