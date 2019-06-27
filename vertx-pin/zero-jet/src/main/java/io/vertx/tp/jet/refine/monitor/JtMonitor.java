package io.vertx.tp.jet.refine.monitor;

import io.vertx.core.json.JsonObject;

public class JtMonitor {
    /*
     * Single monitor for starting
     */
    public static void startRouter(final Class<?> clazz, final JsonObject config) {
        JtRouter.start(clazz, config);
    }
}
