package io.vertx.up.micro.follow;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.zero.epic.Ut;

import java.lang.reflect.Method;

/**
 * Envelop method(Envelop)
 */
public class SyncInvoker extends AbstractInvoker {

    @Override
    public void ensure(final Class<?> returnType,
                       final Class<?> paramCls) {
        // Verify
        final boolean valid =
                Envelop.class == returnType && paramCls == Envelop.class;
        InvokerUtil.verify(!valid, returnType, paramCls, this.getClass());
    }

    @Override
    public void invoke(final Object proxy,
                       final Method method,
                       final Message<Envelop> message) {
        // Invoke directly
        final Envelop envelop = message.body();
        this.getLogger().info(Info.MSG_FUTURE, this.getClass(), method.getReturnType(), false);
        message.reply(Ut.invoke(proxy, method.getName(), envelop));
    }

    @Override
    public void next(final Object proxy,
                     final Method method,
                     final Message<Envelop> message,
                     final Vertx vertx) {
        final Envelop envelop = message.body();
        this.getLogger().info(Info.MSG_FUTURE, this.getClass(), method.getReturnType(), true);
        final Envelop result = Ut.invoke(proxy, method.getName(), envelop);
        this.nextEnvelop(vertx, method, result)
                .setHandler(Ux.handler(message));
    }
}
