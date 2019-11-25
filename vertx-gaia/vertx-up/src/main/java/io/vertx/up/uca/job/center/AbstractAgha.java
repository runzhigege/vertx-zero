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
import io.vertx.up.fn.Actuator;
import io.vertx.up.log.Annal;
import io.vertx.up.uca.job.phase.Phase;
import io.vertx.up.uca.job.store.JobConfig;
import io.vertx.up.uca.job.store.JobPin;
import io.vertx.up.uca.job.store.JobStore;
import io.vertx.up.uca.job.timer.Interval;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractAgha implements Agha {

    private static final JobConfig CONFIG = JobPin.getConfig();
    private static final AtomicBoolean SELECTED = new AtomicBoolean(Boolean.TRUE);
    /*
     * STARTING ------|
     *                v
     *     |------> READY <-------------------|
     *     |          |                       |
     *     |          |                    <resume>
     *     |          |                       |
     *     |        <start>                   |
     *     |          |                       |
     *     |          V                       |
     *     |        RUNNING --- <stop> ---> STOPPED
     *     |          |
     *     |          |
     *  <resume>   ( error )
     *     |          |
     *     |          |
     *     |          v
     *     |------- ERROR
     *
     */
    private static final ConcurrentMap<JobStatus, JobStatus> MOVING = new ConcurrentHashMap<JobStatus, JobStatus>() {
        {
            /* STARTING -> READY */
            this.put(JobStatus.STARTING, JobStatus.READY);

            /* READY -> RUNNING ( Automatically ) */
            this.put(JobStatus.READY, JobStatus.RUNNING);

            /* RUNNING -> STOPPED ( Automatically ) */
            this.put(JobStatus.RUNNING, JobStatus.STOPPED);

            /* STOPPED -> READY */
            this.put(JobStatus.STOPPED, JobStatus.READY);

            /* ERROR -> READY */
            this.put(JobStatus.ERROR, JobStatus.READY);
        }
    };
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

    private Future<Envelop> working(final Mission mission) {
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
        final String code = mission.getCode();
        final WorkerExecutor executor =
                this.vertx.createSharedWorkerExecutor(code, 1, threshold);
        this.getLogger().info(Info.JOB_POOL_START, code, String.valueOf(TimeUnit.NANOSECONDS.toSeconds(threshold)));

        final Promise<Envelop> finalize = Promise.promise();
        /*
         * The executor start to process the workers here.
         */
        executor.<Envelop>executeBlocking(
                promise -> promise.handle(this.workingAsync(mission)),
                handler -> {
                    executor.close();
                    this.getLogger().info(Info.JOB_POOL_END, code);
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
        final Phase phase = Phase.start(mission.getCode())
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

    void working(final Mission mission, final Actuator actuator) {
        if (JobStatus.READY == mission.getStatus()) {
            /*
             * READY -> RUNNING
             */
            this.moveOn(mission, true);
            /*
             * Running the job next time when current job get event
             * from event bus trigger
             */
            this.working(mission).compose(envelop -> {
                /*
                 * Complete future and returned: Async
                 */
                actuator.execute();
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
    }

    void moveOn(final Mission mission, final boolean noError) {
        if (noError) {
            /*
             * Preparing for job
             **/
            if (MOVING.containsKey(mission.getStatus())) {
                /*
                 * Next Status
                 */
                final JobStatus moved = MOVING.get(mission.getStatus());
                final JobStatus original = mission.getStatus();
                mission.setStatus(moved);
                /*
                 * Log and update cache
                 */
                this.getLogger().info(Info.JOB_MOVED, mission.getType(), mission.getCode(), original, moved);
                this.store().update(mission);
            }
        } else {
            /*
             * Terminal job here
             */
            if (JobStatus.RUNNING == mission.getStatus()) {
                mission.setStatus(JobStatus.ERROR);
                this.getLogger().info(Info.JOB_TERMINAL, mission.getCode());
                this.store().update(mission);
            }
        }
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
