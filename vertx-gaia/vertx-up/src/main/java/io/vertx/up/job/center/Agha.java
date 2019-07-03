package io.vertx.up.job.center;

import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.em.JobType;

/**
 * Job manager to manage each job here.
 */
public interface Agha {

    static Agha get(final JobType type) {
        return Pool.AGHAS.getOrDefault(type, PlanAgha::new).get();
    }

    /**
     * Start new job by definition of Mission here.
     */
    boolean begin(Mission mission);

    /**
     * End current job here.
     */
    boolean end(Mission mission);
}
