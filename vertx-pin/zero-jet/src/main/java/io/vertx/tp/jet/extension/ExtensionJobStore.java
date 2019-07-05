package io.vertx.tp.jet.extension;

import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.store.JobStore;

import java.util.Set;

/**
 * Database job store that will be used in `vertx-jet`.
 */
public class ExtensionJobStore implements JobStore {

    @Override
    public JobStore remove(final Mission mission) {
        return null;
    }

    @Override
    public JobStore update(final Mission mission) {
        return null;
    }

    @Override
    public JobStore add(final Mission mission) {
        return null;
    }

    @Override
    public Set<Mission> fetch() {
        return null;
    }

    @Override
    public Mission fetch(final String name) {
        return null;
    }
}
