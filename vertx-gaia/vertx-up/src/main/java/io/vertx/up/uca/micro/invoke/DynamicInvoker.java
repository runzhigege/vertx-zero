package io.vertx.up.uca.micro.invoke;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.up.commune.Envelop;
import io.vertx.up.unity.Ux;

import java.lang.reflect.Method;

public class DynamicInvoker extends AbstractInvoker {

    @Override
    public void ensure(final Class<?> returnType, final Class<?> paramCls) {
        // Verify
        final boolean valid =
                (void.class != returnType && Void.class != returnType);
        InvokerUtil.verify(!valid, returnType, paramCls, getClass());
    }

    @Override
    public void invoke(final Object proxy,
                       final Method method,
                       final Message<Envelop> message) {
        final Envelop envelop = message.body();
        getLogger().info(Info.MSG_FUTURE, getClass(), method.getReturnType(), false);
        final Object returnValue = invokeInternal(proxy, method, envelop);
        // The returnValue type could not be Future
        message.reply(Envelop.success(returnValue));
    }

    @Override
    public void next(final Object proxy,
                     final Method method,
                     final Message<Envelop> message,
                     final Vertx vertx) {
        final Envelop envelop = message.body();
        getLogger().info(Info.MSG_FUTURE, getClass(), method.getReturnType(), true);
        final Object returnValue = invokeInternal(proxy, method, envelop);
        nextEnvelop(vertx, method, Envelop.success(returnValue))
                .setHandler(Ux.handler(message));
    }
}
