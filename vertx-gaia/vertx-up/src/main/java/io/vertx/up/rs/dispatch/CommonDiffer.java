package io.vertx.up.rs.dispatch;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.hunt.PingAim;
import io.vertx.up.rs.hunt.SyncAim;
import io.vertx.up.util.Ut;
import io.vertx.up.fn.Fn;

import java.lang.reflect.Method;

/**
 * EventBus disabled for request
 * 1. SyncAim: Non-Event Bus: Request -> Response
 * 2. BlockAim: Non-Event Bus: Request -> (TRUE/FALSE)
 */
class CommonDiffer implements Differ<RoutingContext> {

    private static Differ<RoutingContext> INSTANCE = null;

    private CommonDiffer() {
    }

    public static Differ<RoutingContext> create() {
        if (null == INSTANCE) {
            synchronized (EventDiffer.class) {
                if (null == INSTANCE) {
                    INSTANCE = new CommonDiffer();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Aim<RoutingContext> build(final Event event) {
        final Method method = event.getAction();
        final Class<?> returnType = method.getReturnType();
        Aim<RoutingContext> aim = null;
        if (Void.class == returnType || void.class == returnType) {
            // Mode 4: Non-Event Bus: One-Way
            aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "-mode-ping",
                    () -> Ut.instance(PingAim.class));
        } else {
            // Mode 2: Non-Event Bus: Request-Response\
            aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "-mode-sync",
                    () -> Ut.instance(SyncAim.class));
        }
        return aim;
    }
}
