package io.vertx.up.uca.job.center;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.util.Ut;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class FixedAgha extends AbstractAgha {

    @Override
    public Future<Long> begin(final Mission mission) {
        /*
         * Calculate started for delay output
         */
        final long delay = this.getDelay(mission);
        /*
         * STARTING -> READY
         **/
        this.moveOn(mission, true);

        final Promise<Long> future = Promise.promise();
        final long jobId = this.interval().startAt(delay, mission.getDuration(), (timeId) -> this.working(mission, () -> {
            /*
             * Complete future and returned Async
             */
            future.tryComplete(timeId);
            /*
             * RUNNING -> STOPPED -> READY
             */
            Ut.itRepeat(2, () -> this.moveOn(mission, true));
        }));
        this.getLogger().info(Info.JOB_INTERVAL, mission.getCode(),
                String.valueOf(delay), String.valueOf(mission.getDuration()), String.valueOf(jobId));
        return future.future();
    }

    private long getDelay(final Mission mission) {
        final Instant end = mission.getInstant();
        final Instant start = Instant.now();
        final long delay = ChronoUnit.MILLIS.between(start, end);
        if (0 < delay) {
            this.getLogger().info(Info.JOB_DELAY, mission.getCode(), String.valueOf(delay));
        }
        return delay < 0 ? 0L : delay;
    }
}
