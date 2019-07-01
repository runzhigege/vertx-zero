package io.vertx.tp.ke.booter;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

class BtVertx {

    private static final Vertx VERTX;

    static {
        /* Prepare another vert.x instance */
        final VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(30000000000L);
        VERTX = Vertx.vertx(options);
    }

    static Vertx getVertx() {
        return VERTX;
    }
}
