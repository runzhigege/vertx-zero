package io.vertx.up.uca.job.center;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.Envelop;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.Values;
import io.vertx.up.eon.em.JobStatus;
import io.vertx.up.log.Annal;
import io.vertx.up.uca.job.phase.Phase;
import io.vertx.up.uca.job.store.JobConfig;
import io.vertx.up.uca.job.store.JobPin;
import io.vertx.up.uca.job.store.JobStore;
import io.vertx.up.uca.job.timer.Interval;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractAgha implements Agha {

    private static final JobConfig CONFIG = JobPin.getConfig();
    private static final AtomicBoolean SELECTED = new AtomicBoolean(Boolean.TRUE);

    @Contract
    private transient Vertx vertx;

    Interval interval() {
        final Class<?> intervalCls = CONFIG.getInterval().getComponent();
        final Interval interval = Ut.singleton(intervalCls);
        Ut.contract(interval, Vertx.class, this.vertx);
        if (SELECTED.getAndSet(Boolean.FALSE)) {
            /* Be sure the log only provide once */
            this.getLogger().info(Info.JOB_COMPONENT_SELECTED, "Interval", interval.getClass().getName());
        }
        return interval;
    }

    @SuppressWarnings("all")
    JobStore store() {
        return JobPin.getStore();
    }

    Future<Envelop> working(final Mission mission) {
        long threshold = mission.getThreshold();
        if (Values.RANGE == threshold) {
            /*
             * Zero here
             */
            threshold = TimeUnit.MINUTES.toNanos(5);
        }
        /*
         * Worker Executor of New created
         * 1) Create new worker pool for next execution here
         * 2) Do not break the major thread for terminal current job
         * 3）Executing log here for long block issue
         */
        final String name = mission.getName();
        final WorkerExecutor executor =
                this.vertx.createSharedWorkerExecutor(name, 1, threshold);
        this.getLogger().info(Info.JOB_POOL_START, name, String.valueOf(TimeUnit.NANOSECONDS.toSeconds(threshold)));
        final Promise<Envelop> finalize = Promise.promise();
        /*
         * The executor start to process the workers here.
         */
        executor.<Envelop>executeBlocking(
                promise -> promise.handle(this.workingAsync(mission)),
                handler -> {
                    executor.close();
                    this.getLogger().info(Info.JOB_POOL_END, name);
                    finalize.handle(handler);
                });
        return finalize.future();
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
    private Future<Envelop> workingAsync(final Mission mission) {
        /*
         * Initializing phase reference here.
         */
        final Phase phase = Phase.start(mission.getName())
                .bind(this.vertx)
                .bind(mission);
        /*
         * 1. Step 1:  EventBus ( Input )
         */
        return phase.inputAsync(mission)
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
                .compose(phase::callbackAsync)
                /* Otherwise exception */
                .otherwise(Ux.otherwise());
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
            this.getLogger().info(Info.JOB_READY, mission.getName());
            this.store().update(mission);
        }
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
