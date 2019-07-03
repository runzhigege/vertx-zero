package io.vertx.up.job.center;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.store.JobConfig;
import io.vertx.up.job.store.JobPin;
import io.vertx.up.job.timer.Interval;
import io.zero.epic.Ut;

public abstract class AbstractAgha implements Agha {

    private static final JobConfig CONFIG = JobPin.getConfig();

    @Contract
    private transient Vertx vertx;

    Interval interval() {
        final Class<?> intervalCls = CONFIG.getInterval().getComponent();
        final Interval interval = Ut.singleton(intervalCls);
        Ut.contract(interval, Vertx.class, this.vertx);
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
                .compose(phase::incomeAsync);
    }
}
