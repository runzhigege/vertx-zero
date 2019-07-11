package io.vertx.up.uca.micro.invoke;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.up.commune.Envelop;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.lang.reflect.Method;

/**
 * Future<T> method(I)
 */
public class AsyncInvoker extends AbstractInvoker {

    @Override
    public void ensure(final Class<?> returnType, final Class<?> paramCls) {
        // Verify
        final boolean valid =
                (void.class != returnType && Void.class != returnType);
        InvokerUtil.verify(!valid, returnType, paramCls, getClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(final Object proxy,
                       final Method method,
                       final Message<Envelop> message) {
        final Envelop envelop = message.body();
        // Deserialization from message bus.
        final Class<?> returnType = method.getReturnType();
        getLogger().info(Info.MSG_FUTURE, getClass(), returnType, false);
        // Get T
        final Class<?> tCls = returnType.getComponentType();
        if (Envelop.class == tCls) {
            // Input type is Envelop, input directly
            final Future<Envelop> result = Ut.invoke(proxy, method.getName(), envelop);
            result.setHandler(item -> message.reply(item.result()));
        } else {
            final Object returnValue = invokeInternal(proxy, method, envelop);
            if (null == returnValue) {
                final Future future = Future.future();
                future.setHandler(Ux.handler(message));
            } else {
                final Future future = (Future) returnValue;
                future.setHandler(Ux.handler(message));
            }
            /*
            final Object reference = envelop.data();
            final Object arguments = Ut.deserialize(Ut.toString(reference), argType);
            final Future tResult = Ut.invoke(proxy, method.name(), arguments);
            tResult.setHandler(Ux.handler(message));
            */
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void next(final Object proxy,
                     final Method method,
                     final Message<Envelop> message,
                     final Vertx vertx) {
        final Envelop envelop = message.body();
        // Deserialization from message bus.
        final Class<?> returnType = method.getReturnType();
        getLogger().info(Info.MSG_FUTURE, getClass(), returnType, true);
        // Get T
        final Class<?> tCls = returnType.getComponentType();
        if (Envelop.class == tCls) {
            // Input type is Envelop, input directly
            final Future<Envelop> result = Ut.invoke(proxy, method.getName(), envelop);
            /* replaced old cold
            result.compose(item -> TunnelClient.create(this.getClass())
                    .connect(vertx)
                    .connect(method)
                    .send(item))
                    .setHandler(Ux.handler(message)); */
            result.compose(nextEnvelop(vertx, method))
                    .setHandler(Ux.handler(message));
        } else {
            final Future future = invokeJson(proxy, method, envelop);
            /* replaced old code
            future.compose(item -> TunnelClient.create(this.getClass())
                    .connect(vertx)
                    .connect(method)
                    .send(Ux.to(item)))
                    .compose(item -> Future.succeededFuture(Ux.to(item)))
                    .setHandler(Ux.handler(message)); */
            future.compose(nextEnvelop(vertx, method))
                    .setHandler(Ux.handler(message));
        }
    }
}
