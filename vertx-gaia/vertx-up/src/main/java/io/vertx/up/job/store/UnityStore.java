package io.vertx.up.job.store;

import io.vertx.up.atom.worker.Mission;
import io.vertx.up.plugin.job.JobPool;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Bridge for different JobStore
 */
class UnityStore implements JobStore {

    static JobStore INSTANCE;
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
        final Set<Mission> missions = this.reader.fetch();
        final Set<Mission> storage = this.store.fetch();
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
        return this.store.add(mission);
    }

    @Override
    public Mission fetch(final String name) {
        return JobPool.get(name, () -> {
            Mission mission = this.reader.fetch(name);
            if (Objects.isNull(mission)) {
                mission = this.store.fetch(name);
            }
            return mission;
        });
    }

    @Override
    public JobStore remove(final Mission mission) {
        JobPool.remove(mission.getName());
        return this.store.remove(mission);
    }

    @Override
    public JobStore update(final Mission mission) {
        JobPool.save(mission);
        return this.store.update(mission);
    }
}
