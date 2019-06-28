package io.vertx.tp.jet.monitor;

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
    private static final AtomicBoolean AGENT_CONFIG = new AtomicBoolean(Boolean.FALSE);

    void start(final Annal logger, final JsonObject config) {
        if (!AGENT_CONFIG.getAndSet(Boolean.TRUE)) {
            Runner.run(() -> Jt.infoRoute(logger, JtMsg.AGENT_CONFIG, config.encode()), "jet-agent-config");
        }
    }
}
