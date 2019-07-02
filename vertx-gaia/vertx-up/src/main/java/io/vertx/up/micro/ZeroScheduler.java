package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.up.annotations.Worker;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.job.center.Agha;
import io.vertx.up.job.store.JobStore;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;

import java.util.Set;

/**
 * Background worker of Zero framework, it's for schedule of background tasks here.
 * This scheduler is for task deployment, it should deploy all tasks
 * This worker must be SINGLE ( instances = 1 ) because multi worker with the same tasks may be
 * conflicts
 */
@Worker(instances = Values.SINGLE)
public class ZeroScheduler extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroScheduler.class);
    private static final JobStore STORE = JobStore.get();

    @Override
    public void start() {
        /* Pick Up all Mission definition from system */
        final Set<Mission> missions = STORE.fetch();
        /* Whether there exist Mission definition */
        if (missions.isEmpty()) {
            LOGGER.info(Info.JOB_EMPTY);
        } else {
            LOGGER.info(Info.JOB_MONITOR, missions.size());
            /* Start each job here by different types */
            missions.forEach(mission ->
                    Agha.get(mission.getType())
                            .start(mission));
        }
    }
}
