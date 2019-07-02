package io.vertx.up.job.center;

import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.em.JobType;

/**
 * Job manager to manage each job here.
 */
public interface Agha {

    static Agha get(final JobType type) {
        return Funs.POOL.getOrDefault(type, PlanAgha::new).get();
    }

    /**
     * Start new job by definition of Mission here.
     */
    boolean start(Mission mission);
}
