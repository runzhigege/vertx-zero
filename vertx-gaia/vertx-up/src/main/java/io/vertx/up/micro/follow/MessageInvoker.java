package io.vertx.up.micro.follow;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;
import io.vertx.up.tool.mirror.Instance;

import java.lang.reflect.Method;

/**
 * void method(Messsage<Envelop>)
 */
public class MessageInvoker implements Invoker {
    @Override
    public void ensure(final Class<?> returnType,
                       final Class<?> paramCls) {
        // Verify
        final boolean valid =
                (void.class == returnType || Void.class == returnType)
                        && Message.class.isAssignableFrom(paramCls);
        InvokerUtil.verify(!valid, returnType, paramCls, this.getClass());
    }

    @Override
    public void invoke(final Object proxy,
                       final Method method,
                       final Message<Envelop> message) {
        // Invoker and do not reply
        Instance.invoke(proxy, method.getName(), message);
    }

    @Override
    public void next(final Object proxy,
                     final Method method,
                     final Message<Envelop> message,
                     final Vertx vertx) {

    }
}
