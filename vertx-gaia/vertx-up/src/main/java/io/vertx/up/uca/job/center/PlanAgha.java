package io.vertx.up.uca.job.center;

import io.vertx.core.Future;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.em.JobStatus;

class PlanAgha extends AbstractAgha {

    @Override
    public Future<Long> begin(final Mission mission) {
        final Future<Long> future = Future.future();
        /*
         * Preparing for job
         **/
        preparing(mission);

        interval().startAt(mission.getDuration(), (timeId) -> {
            if (JobStatus.READY == mission.getStatus()) {
                /*
                 * Running the job
                 */
                working(mission).compose(envelop -> {
                    /*
                     * Complete future and returned Async
                     */
                    future.complete(timeId);
                    return Future.succeededFuture(envelop);
                });
            }
        });
        return future;
    }
}
