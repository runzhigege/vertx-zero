package io.vertx.up.job.center;

import io.vertx.core.Future;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.JobStatus;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class FixedAgha extends AbstractAgha {

    @Override
    public Future<Long> begin(final Mission mission) {
        /*
         * Calculate started for delay output
         */
        final long delay = this.getDelay(mission);
        final Future<Long> future = Future.future();
        /*
         * Preparing for job
         **/
        this.preparing(mission);

        this.interval().startAt(delay, mission.getDuration(), (timeId) -> {
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
        return future;
    }

    private long getDelay(final Mission mission) {
        final Instant end = mission.getInstant();
        final Instant start = Instant.now();
        final long delay = ChronoUnit.MILLIS.between(start, end);
        this.getLogger().info(Info.JOB_DELAY, mission.getName(), String.valueOf(delay));
        return delay < 0 ? 0L : delay;
    }
}
