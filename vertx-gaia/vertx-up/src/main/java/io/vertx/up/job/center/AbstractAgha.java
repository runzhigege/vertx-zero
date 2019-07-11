package io.vertx.up.job.center;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.JobStatus;
import io.vertx.up.job.phase.Phase;
import io.vertx.up.job.store.JobConfig;
import io.vertx.up.job.store.JobPin;
import io.vertx.up.job.store.JobStore;
import io.vertx.up.job.timer.Interval;
import io.vertx.up.log.Annal;
import io.vertx.zero.epic.Ut;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractAgha implements Agha {

    private static final JobConfig CONFIG = JobPin.getConfig();
    private static final AtomicBoolean SELECTED = new AtomicBoolean(Boolean.TRUE);

    @Contract
    private transient Vertx vertx;

    Interval interval() {
        final Class<?> intervalCls = CONFIG.getInterval().getComponent();
        final Interval interval = Ut.singleton(intervalCls);
        Ut.contract(interval, Vertx.class, vertx);
        if (SELECTED.getAndSet(Boolean.FALSE)) {
            /* Be sure the log only provide once */
            getLogger().info(Info.JOB_COMPONENT_SELECTED, "Interval", interval.getClass().getName());
        }
        return interval;
    }

    JobStore store() {
        return JobPin.getStore();
    }

    /*
     * Input workflow for Mission
     * 1. Whether address configured ?
     *    - Yes, get Envelop from event bus as secondary input
     *    - No, get Envelop of `Envelop.ok()` instead
     * 2. Extract `JobIncome`
     * 3. Major
     * 4. JobOutcome
     * 5. Whether defined address of output
     * 6. If 5, provide callback function of this job here.
     */
    Future<Envelop> working(final Mission mission) {
        /*
         * Initializing phase reference here.
         */
        final Phase phase = Phase.start(mission.getName())
                .bind(vertx)
                .bind(mission);

        return Ux.toFuture(mission)
                /*
                 * 1. Step 1:  EventBus ( Input )
                 */
                .compose(phase::inputAsync)
                /*
                 * 2. Step 2:  JobIncome ( Process )
                 */
                .compose(phase::incomeAsync)
                /*
                 * 3. Step 3:  Major cole logical here
                 */
                .compose(phase::invokeAsync)
                /*
                 * 4. Step 4:  JobOutcome ( Process )
                 */
                .compose(phase::outcomeAsync)
                /*
                 * 5. Step 5: EventBus ( Output )
                 */
                .compose(phase::outputAsync)
                /*
                 * 6. Final steps here
                 */
                .compose(phase::callbackAsync);
    }

    protected void preparing(final Mission mission) {
        /*
         * Preparing for job
         **/
        if (JobStatus.STARTING == mission.getStatus()) {
            /*
             * STARTING -> READY
             * */
            mission.setStatus(JobStatus.READY);
            getLogger().info(Info.JOB_READY, mission.getName());
            store().update(mission);
        }
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }
}
