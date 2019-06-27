package io.vertx.tp.jet;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.tp.optic.environment.AmbientEnvironment;
import io.vertx.up.plugin.extension.PlugRouter;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

/*
 * Agent entry of dynamic deployment, this component is mount to router also.
 * 1) The dynamic router could be authorized by zero @Wall class
 * 2) The dynamic router will call connection pool of configuration, will manage all the routers in current system.
 * 3) The dynamic router will registry the routers information when booting
 */
public class JetPollux implements PlugRouter {
    /*
     * Multi Application environment here
     */
    private static final ConcurrentMap<String, AmbientEnvironment> AMBIENT = Ambient.getEnvironments();
    private transient JetCastor castor;

    @Override
    public void mount(final Router router, final JsonObject config) {
        // 「Booting」( Multi application deployment on Router )

    }

    /*
     * Bind two components to the same Vertx instance
     */
    @Override
    public void bind(final Vertx vertx) {
        if (Objects.nonNull(vertx)) {
            this.castor = JetCastor.create(vertx);
        }
    }
}
