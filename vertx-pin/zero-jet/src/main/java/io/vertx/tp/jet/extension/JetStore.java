package io.vertx.tp.jet.extension;

import io.vertx.tp.jet.atom.JtJob;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.tp.optic.environment.AmbientEnvironment;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.store.JobStore;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Database job store that will be used in `vertx-jet`.
 */
public class JetStore implements JobStore {

    private static final ConcurrentMap<String, AmbientEnvironment> ENVS =
            Ambient.getEnvironments();

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
        return ENVS.values().stream().filter(Objects::nonNull)
                .flatMap(environment -> environment.jobs().stream())
                .map(JtJob::toJob)
                .collect(Collectors.toSet());
    }

    @Override
    public Mission fetch(final String name) {
        return null;
    }
}
