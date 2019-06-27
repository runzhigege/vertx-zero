package io.vertx.tp.jet;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.tp.jet.atom.JtConfig;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.jet.refine.monitor.JtMonitor;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.tp.optic.environment.AmbientEnvironment;
import io.vertx.up.plugin.extension.PlugRouter;
import io.zero.epic.Ut;

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
        JtMonitor.startRouter(this.getClass(), config);
        /*
         * 「Booting」( Multi application deployment on Router )
         */
        AMBIENT.keySet().stream()
                .flatMap(appId -> AMBIENT.get(appId).routes().stream()
                        /* Bind appId */
                        .map(uri -> uri.bind(appId))
                )
                /*
                 * Start up and bind `order` and `config`
                 */
                .map(uri -> uri.bind(this.getOrder())
                        .bind(Ut.deserialize(config.copy(), JtConfig.class)))
                .forEach(uri -> {
                    // 「Route」
                    final Route route = router.route();

                    // Single route registry
                    this.registryUri(route, uri);
                });
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

    private void registryUri(final Route route, final JtUri uri) {
        // Uri, Method, Order
        route.path(uri.path()).order(uri.order()).method(uri.method());
        // Consumes / Produces
        uri.consumes().forEach(route::consumes);
        uri.produces().forEach(route::produces);
        /*
         * Major Route: EngineAim
         * 1) Pre-Condition
         *      IN_RULE
         *      IN_MAPPING
         *      IN_PLUG
         *      IN_SCRIPT
         * 2) Major code logical ( Could not be configured )
         * 3) Send logical
         *      3.1) Send current request to worker ( Ha )
         *      3.2) Send message to worker
         *      3.3) Let worker consume component
         */

    }
}
