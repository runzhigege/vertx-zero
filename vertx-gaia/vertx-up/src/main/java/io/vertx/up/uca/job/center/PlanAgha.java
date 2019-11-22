package io.vertx.up.uca.job.center;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.Envelop;
import io.vertx.up.eon.em.JobStatus;
import io.vertx.up.util.Ut;

class PlanAgha extends AbstractAgha {

    @Override
    public Future<Long> begin(final Mission mission) {
        final Promise<Long> future = Promise.promise();
        /*
         * STARTING -> READY
         **/
        this.moveOn(mission, true);

        this.interval().startAt(mission.getDuration(), (timeId) -> {
            if (JobStatus.READY == mission.getStatus()) {
                /*
                 * READY -> RUNNING
                 */
                this.moveOn(mission, true);
                /*
                 * Running the job
                 */
                this.working(mission).compose(envelop -> {
                    /*
                     * Complete future and returned Async
                     */
                    future.tryComplete(timeId);
                    /*
                     * RUNNING -> STOPPED -> READY
                     */
                    Ut.itRepeat(2, () -> this.moveOn(mission, true));
                    return Future.succeededFuture(envelop);
                }).otherwise(error -> {
                    /*
                     * RUNNING -> ERROR
                     */
                    this.moveOn(mission, false);
                    error.printStackTrace();
                    return Envelop.failure(error);
                });
            }
        });
        return future.future();
    }
}
