package io.vertx.up.job.center;

import io.vertx.up.eon.em.JobType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {
    ConcurrentMap<JobType, Agha> AGHAS = new ConcurrentHashMap<JobType, Agha>() {
        {
            this.put(JobType.FIXED, new FixedAgha());
            this.put(JobType.ONCE, new OnceAgha());
            this.put(JobType.PLAN, new PlanAgha());
        }
    };
}


