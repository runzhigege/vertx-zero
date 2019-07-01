package io.vertx.up.micro.follow;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;

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
        InvokerUtil.verify(!valid, returnType, paramCls, this.getClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(final Object proxy,
                       final Method method,
                       final Message<Envelop> message) {
        final Envelop envelop = message.body();
        // Deserialization from message bus.
        final Class<?> returnType = method.getReturnType();
        this.getLogger().info(Info.MSG_FUTURE, this.getClass(), returnType, false);
        // Get T
        final Class<?> tCls = returnType.getComponentType();
        if (Envelop.class == tCls) {
            // Input type is Envelop, input directly
            final Future<Envelop> result = Ut.invoke(proxy, method.getName(), envelop);
            result.setHandler(item -> message.reply(item.result()));
        } else {
            final Object returnValue = this.invokeInternal(proxy, method, envelop);
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
            final Future tResult = Ut.invoke(proxy, method.getName(), arguments);
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
        this.getLogger().info(Info.MSG_FUTURE, this.getClass(), returnType, true);
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
            result.compose(this.nextEnvelop(vertx, method))
                    .setHandler(Ux.handler(message));
        } else {
            final Future future = this.invokeJson(proxy, method, envelop);
            /* replaced old code
            future.compose(item -> TunnelClient.create(this.getClass())
                    .connect(vertx)
                    .connect(method)
                    .send(Ux.to(item)))
                    .compose(item -> Future.succeededFuture(Ux.to(item)))
                    .setHandler(Ux.handler(message)); */
            future.compose(this.nextEnvelop(vertx, method))
                    .setHandler(Ux.handler(message));
        }
    }
}
