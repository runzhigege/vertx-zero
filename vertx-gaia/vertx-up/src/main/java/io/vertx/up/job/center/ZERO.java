package io.vertx.up.job.center;

import io.vertx.up.eon.em.JobType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

interface Pool {
    ConcurrentMap<JobType, Supplier<Agha>> AGHAS = new ConcurrentHashMap<JobType, Supplier<Agha>>() {
        {
            this.put(JobType.FIXED, FixedAgha::new);
            this.put(JobType.ONCE, OnceAgha::new);
            this.put(JobType.PLAN, PlanAgha::new);
        }
    };
}


