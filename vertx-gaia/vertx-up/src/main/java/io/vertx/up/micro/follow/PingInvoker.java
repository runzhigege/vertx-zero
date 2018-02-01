package io.vertx.up.micro.follow;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;
import io.vertx.up.tool.mirror.Instance;

import java.lang.reflect.Method;

/**
 * void method(Envelop)
 */
public class PingInvoker implements Invoker {

    @Override
    public void ensure(final Class<?> returnType,
                       final Class<?> paramCls) {
        final boolean valid = (void.class == returnType || Void.class == returnType)
                && paramCls == Envelop.class;
        InvokerUtil.verify(!valid, returnType, paramCls, getClass());
    }

    @Override
    public void invoke(final Object proxy,
                       final Method method,
                       final Message<Envelop> message) {
        // Invoke directly
        final Envelop envelop = message.body();
        Instance.invoke(proxy, method.getName(), envelop);
        message.reply(Envelop.success(Boolean.TRUE));
    }
}
