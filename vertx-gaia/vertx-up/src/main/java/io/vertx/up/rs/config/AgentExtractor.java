package io.vertx.up.rs.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.equip.DeployRotate;
import io.vertx.up.rs.equip.Rotate;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

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
        final Rotate rotate = Ut.singleton(DeployRotate.class);

        return Fn.pool(OPTIONS, clazz, () -> rotate.spinAgent(clazz));
    }
}
