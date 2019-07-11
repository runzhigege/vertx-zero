package io.vertx.up.job.store;

import io.vertx.tp.plugin.job.JobPool;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.log.Annal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Bridge for different JobStore
 */
class UnityStore implements JobStore {

    private static final Annal LOGGER = Annal.get(UnityStore.class);
    /*
     * Code in programming here ( Could not modify, read-only )
     */
    private final transient JobReader reader = new CodeStore();
    /*
     * Storage for job definition ( Could be modified )
     */
    private final transient JobStore store = new ExtensionStore();

    @Override
    public Set<Mission> fetch() {
        final Set<Mission> missions = reader.fetch();
        LOGGER.info(Info.JOB_SCANNED, missions.size(), "Programming");
        final Set<Mission> storage = store.fetch();
        LOGGER.info(Info.JOB_SCANNED, storage.size(), "Dynamic/Stored");
        /* Merged */
        final Set<Mission> result = new HashSet<>();
        result.addAll(missions);
        result.addAll(storage);

        /* Job Pool Sync */
        JobPool.put(result);
        return result;
    }

    @Override
    public JobStore add(final Mission mission) {
        JobPool.save(mission);
        return store.add(mission);
    }

    @Override
    public Mission fetch(final String name) {
        return JobPool.get(name, () -> {
            Mission mission = reader.fetch(name);
            if (Objects.isNull(mission)) {
                mission = store.fetch(name);
            }
            return mission;
        });
    }

    @Override
    public JobStore remove(final Mission mission) {
        JobPool.remove(mission.getName());
        return store.remove(mission);
    }

    @Override
    public JobStore update(final Mission mission) {
        JobPool.save(mission);
        return store.update(mission);
    }
}
