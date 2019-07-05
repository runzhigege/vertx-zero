package io.vertx.tp.jet.extension;

import io.vertx.tp.optic.environment.Ambient;
import io.vertx.tp.optic.environment.AmbientEnvironment;

import java.util.concurrent.ConcurrentMap;

/**
 * Pool to connect to IJob `Pojo` class.
 */
class ExtensionJobPool {
    private static final ConcurrentMap<String, AmbientEnvironment> ENVS =
            Ambient.getEnvironments();

}
