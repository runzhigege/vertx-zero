package io.vertx.up.rs.router;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.PlugRouter;
import io.vertx.up.web.ZeroAmbient;
import io.vertx.zero.eon.Values;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class DynamicAxis implements Axis<Router> {

    private static final String KEY_ROUTER = "router";
    private static final Annal LOGGER = Annal.get(DynamicAxis.class);
    private static final AtomicInteger LOG_FLAG_START = new AtomicInteger(0);
    private static final AtomicInteger LOG_FLAG_END = new AtomicInteger(0);
    private static final transient String NAME = DynamicAxis.class.getSimpleName();
    private transient final Node<JsonObject> uniform = Ut.singleton(ZeroUniform.class);

    @Override
    public void mount(final Router router) {
        final Class<?> clazz = ZeroAmbient.getPlugin(KEY_ROUTER);
        if (Values.ZERO == LOG_FLAG_START.getAndIncrement()) {
            LOGGER.info(Info.DY_DETECT, NAME);
        }
        if (null != clazz && Ut.isImplement(clazz, PlugRouter.class)) {
            final JsonObject config = this.uniform.read();
            final JsonObject routerConfig = Fn.getNull(new JsonObject(), () -> config.getJsonObject(KEY_ROUTER), config);
            if (Values.ONE == LOG_FLAG_END.getAndIncrement()) {
                LOGGER.info(Info.DY_FOUND, NAME, clazz.getName(), routerConfig.encode());
            }
            // Mount dynamic router
            final PlugRouter plugRouter = Fn.poolThread(Pool.PLUGS,
                    () -> Ut.instance(clazz));
            plugRouter.mount(router, routerConfig);
        } else {
            if (Values.ONE == LOG_FLAG_END.getAndIncrement()) {
                LOGGER.info(Info.DY_SKIP, this.NAME, Fn.getNull(null, clazz::getName, clazz));
            }
        }
    }
}
