package io.vertx.up.rs.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.equip.DeployRotate;
import io.vertx.up.rs.equip.Rotate;
import io.vertx.up.epic.Ut;
import io.vertx.up.fn.Fn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Worker verticle deployment
 */
public class WorkerExtractor implements Extractor<DeploymentOptions> {

    private static final Annal LOGGER = Annal.get(WorkerExtractor.class);

    private static final ConcurrentMap<Class<?>, DeploymentOptions>
            OPTIONS = new ConcurrentHashMap<>();

    @Override
    public DeploymentOptions extract(final Class<?> clazz) {
        Fn.safeNull(() -> LOGGER.info(Info.WORKER_HIT, clazz.getName()), clazz);
        final Rotate rotate = Ut.singleton(DeployRotate.class);

        return Fn.pool(OPTIONS, clazz, () -> rotate.spinWorker(clazz));
    }
}
