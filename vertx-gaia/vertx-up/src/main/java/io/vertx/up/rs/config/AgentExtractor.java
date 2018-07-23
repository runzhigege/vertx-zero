package io.vertx.up.rs.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.up.annotations.Agent;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Standard verticle deployment.
 */
public class AgentExtractor implements Extractor<DeploymentOptions> {

    private static final Annal LOGGER = Annal.get(AgentExtractor.class);

    private static final ConcurrentMap<Class<?>, DeploymentOptions>
            OPTIONS = new ConcurrentHashMap<>();

    @Override
    public DeploymentOptions extract(final Class<?> clazz) {
        Fn.safeNull(() -> LOGGER.info(Info.AGENT_HIT, clazz.getName()), clazz);
        return Fn.pool(OPTIONS, clazz, () -> this.transform(clazz));
    }

    private DeploymentOptions transform(final Class<?> clazz) {
        final Annotation annotation = clazz.getDeclaredAnnotation(Agent.class);
        // 1. Instance
        final int instances = Instance.invoke(annotation, Key.INSTANCES);
        final boolean ha = Instance.invoke(annotation, Key.HA);
        final String group = Instance.invoke(annotation, Key.GROUP);
        // 2. Record Log information
        final DeploymentOptions options = new DeploymentOptions();
        options.setHa(ha);
        options.setInstances(instances);
        options.setIsolationGroup(group);
        // 3. Disabled worker fetures.
        options.setWorker(false);
        LOGGER.info(Info.VTC_OPT, instances, group, ha, options.toJson());
        return options;
    }
}
