package io.vertx.up.job.center;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.cv.JobMsg;
import io.vertx.up.job.phase.Phase;
import io.vertx.up.job.store.JobConfig;
import io.vertx.up.job.store.JobPin;
import io.vertx.up.job.timer.Interval;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

public abstract class AbstractAgha implements Agha {

    private static final JobConfig CONFIG = JobPin.getConfig();

    @Contract
    private transient Vertx vertx;

    private transient Phase phase;

    Interval interval() {
        final Class<?> intervalCls = CONFIG.getInterval().getComponent();
        final Interval interval = Ut.singleton(intervalCls);
        Ut.contract(interval, Vertx.class, this.vertx);
        this.getLogger().info(JobMsg.COMPONENT_SELECTED, "Interval", interval.getClass().getName());
        return interval;
    }

    /*
     * Input workflow for Mission
     * 1. Whether address configured ?
     *    - Yes, get Envelop from event bus as secondary input
     *    - No, get Envelop of `Envelop.ok()` instead
     * 2. Extract `JobIncome` and execute
     */
    protected Future<Envelop> before(final Mission mission) {
        /*
         * Initializing phase reference here.
         */
        final Phase phase = Phase.start(mission.getName())
                .bind(this.vertx)
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

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
