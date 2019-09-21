package io.vertx.up.uca.job.center;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.em.JobStatus;

class PlanAgha extends AbstractAgha {

    @Override
    public Future<Long> begin(final Mission mission) {
        final Promise<Long> future = Promise.promise();
        /*
         * Preparing for job
         **/
        this.preparing(mission);

        this.interval().startAt(mission.getDuration(), (timeId) -> {
            if (JobStatus.READY == mission.getStatus()) {
                /*
                 * Running the job
                 */
                this.working(mission).compose(envelop -> {
                    /*
                     * Complete future and returned Async
                     */
                    future.complete(timeId);
                    return Future.succeededFuture(envelop);
                });
            }
        });
        return future.future();
    }
}
