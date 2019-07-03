package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.up.annotations.Worker;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.JobType;
import io.vertx.up.job.center.Agha;
import io.vertx.up.job.store.JobConfig;
import io.vertx.up.job.store.JobPin;
import io.vertx.up.job.store.JobStore;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.zero.epic.Ut;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Background worker of Zero framework, it's for schedule of background tasks here.
 * This scheduler is for task deployment, it should deploy all tasks
 * This worker must be SINGLE ( instances = 1 ) because multi worker with the same tasks may be
 * conflicts
 */
@Worker(instances = Values.SINGLE)
public class ZeroScheduler extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroScheduler.class);
    private static final JobStore STORE = JobPin.getStore();
    private transient final ConcurrentMap<JobType, Agha> agha = new
            ConcurrentHashMap<>();

    public ZeroScheduler() {
        /* Group missions by type, each type should has one reference */
        this.agha.putAll(new ConcurrentHashMap<JobType, Agha>() {
            {
                this.put(JobType.FIXED, Agha.get(JobType.FIXED));
                this.put(JobType.PLAN, Agha.get(JobType.PLAN));
                this.put(JobType.ONCE, Agha.get(JobType.ONCE));
            }
        });
        this.agha.values().forEach(each -> Ut.contract(each, Vertx.class, this.vertx));
    }

    @Override
    public void start() {
        /* Whether contains JobConfig? */
        final JobConfig config = JobPin.getConfig();
        if (Objects.nonNull(config)) {
            /* Pick Up all Mission definition from system */
            final Set<Mission> missions = STORE.fetch();
            /* Whether there exist Mission definition */
            if (missions.isEmpty()) {
                LOGGER.info(Info.JOB_EMPTY);
            } else {
                LOGGER.info(Info.JOB_MONITOR, missions.size());
                /* Start each job here by different types */
                missions.forEach(this::start);
            }
        } else {
            LOGGER.info(Info.JOB_CONFIG_NULL);
        }
    }

    private void start(final Mission mission) {
        /*
         * Prepare for mission, it's verf important to bind mission object to Vertx
         * instead of bind(Vertx) method.
         */
        final Object reference = mission.getProxy();
        if (Objects.nonNull(reference)) {
            /*
             * Bind vertx
             */
            Ut.contract(reference, Vertx.class, this.vertx);
            /*
             * Bind job store ( this method should access job store to process job )
             */
            Ut.contract(reference, JobStore.class, STORE);
        }
        /*
         * Agha calling
         */
        final Agha agha = this.agha.get(mission.getType());
        if (Objects.nonNull(agha)) {
            /*
             * To avoid other use, bind
             */
            /*
             * Bind vertx, because Agha class could get JobStore directly
             * It's not needed to bind JobStore in Agha here.
             */
            Ut.contract(agha, Vertx.class, this.vertx);
            /*
             * Invoke here to provide input
             */
            agha.begin(mission);
        }
    }
}
