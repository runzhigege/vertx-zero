package io.vertx.up.uca.job.store;

import io.vertx.up.atom.worker.Mission;
import io.vertx.up.fn.Fn;
import io.vertx.up.runtime.ZeroAnno;

import java.util.Set;

/**
 * Bridge for different JobStore
 */
class CodeStore implements JobReader {
    private static final Set<Mission> MISSIONS = ZeroAnno.getJobs();

    @Override
    public Set<Mission> fetch() {
        return MISSIONS;
    }

    @Override
    public Mission fetch(final String name) {
        return Fn.getNull(null, () -> MISSIONS.stream()
                .filter(mission -> name.equals(mission.getName()))
                .findFirst().orElse(null), name);
    }
}
