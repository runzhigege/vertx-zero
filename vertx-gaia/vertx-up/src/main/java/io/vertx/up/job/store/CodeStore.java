package io.vertx.up.job.store;

import io.vertx.up.atom.worker.Mission;
import io.vertx.zero.epic.fn.Fn;
import io.vertx.zero.runtime.ZeroAnno;

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
