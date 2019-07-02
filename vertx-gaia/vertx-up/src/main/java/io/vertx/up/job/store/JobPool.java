package io.vertx.up.job.store;

import io.vertx.up.atom.worker.Mission;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * Job Pool in memory or storage
 */
class JobPool {

    private static final ConcurrentMap<String, Mission> JOBS = new ConcurrentHashMap<>();

    static void put(final Set<Mission> missions) {
        missions.forEach(mission -> JOBS.put(mission.getName(), mission));
    }

    static Mission get(final String name, final Supplier<Mission> supplier) {
        return JOBS.getOrDefault(name, supplier.get());
    }

    static void remove(final String name) {
        JOBS.remove(name);
    }

    static void save(final Mission mission) {
        JOBS.put(mission.getName(), mission);
    }
}
