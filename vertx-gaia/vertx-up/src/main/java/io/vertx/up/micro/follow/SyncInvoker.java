package io.vertx.up.micro.follow;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;
import io.vertx.up.tool.mirror.Instance;

import java.lang.reflect.Method;

/**
 * Envelop method(Envelop)
 */
public class SyncInvoker implements Invoker {
    @Override
    public void ensure(final Class<?> returnType,
                       final Class<?> paramCls) {
        // Verify
        final boolean valid =
                Envelop.class == returnType && paramCls == Envelop.class;
        InvokerUtil.verify(!valid, returnType, paramCls, getClass());
    }

    @Override
    public void invoke(final Object proxy,
                       final Method method,
                       final Message<Envelop> message) {
        // Invoke directly
        final Envelop envelop = message.body();
        message.reply(Instance.invoke(proxy, method.getName(), envelop));
    }
}
