package io.vertx.tp.jet.refine.monitor;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtMsg;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.up.log.Annal;
import io.vertx.up.web.Runner;

import java.util.concurrent.atomic.AtomicBoolean;

class JtRouter {
    /*
     * Monitor for router of each App
     */
    private static final AtomicBoolean CONFIG = new AtomicBoolean(Boolean.FALSE);

    static void start(final Class<?> clazz, final JsonObject config) {
        if (!CONFIG.getAndSet(Boolean.TRUE)) {
            Runner.run(() -> {
                final Annal LOGGER = Annal.get(clazz);
                Jt.infoRoute(LOGGER, JtMsg.Route.STARTING_CONFIG, config);
            }, "jet-router-configuration");
        }
    }
}
