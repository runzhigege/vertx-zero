package io.vertx.up.micro.ipc.tower;

import io.vertx.core.Future;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;

import java.lang.reflect.Method;

/**
 * Different return value to build Future<Envelop>
 */
class ReturnTransit {

    private static final Annal LOGGER = Annal.get(ReturnTransit.class);

    @SuppressWarnings("unchecked")
    static Future<Envelop> build(final Object returnValue, final Method method) {
        if (null == returnValue) {
            // Empty Future
            return Future.succeededFuture(Envelop.ok());
        }
        final Class<?> clazz = returnValue.getClass();
        final Future<Envelop> result;
        if (Future.class.isAssignableFrom(clazz)) {
            // Fix Async Server Issue
            final Class<?> tCls = clazz.getComponentType();
            System.out.println(clazz.getComponentType());
            if (Envelop.class == tCls) {
                // Future<Envelop>
                LOGGER.info(Info.MSG_FLOW, "Future<Envelop>", clazz);
                result = (Future<Envelop>) returnValue;
            } else {
                // Future<JsonObject> or Future<JsonArray>
                LOGGER.info(Info.MSG_FLOW, "Future<T>", clazz);
                final Future future = (Future) returnValue;
                return future.compose(item -> Future.succeededFuture(Ux.to(item)));
            }
        } else {
            if (Envelop.class == clazz) {
                // Envelop got
                LOGGER.info(Info.MSG_FLOW, "Envelop", clazz);
                result = Future.succeededFuture((Envelop) returnValue);
            } else {
                LOGGER.info(Info.MSG_FLOW, "Other Type", clazz);
                final Envelop envelop = Envelop.success(returnValue);
                result = Future.succeededFuture(envelop);
            }
        }
        return result;
    }
}
