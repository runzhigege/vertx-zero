package io.vertx.up.micro.follow;

import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.tool.mirror.Types;
import io.vertx.zero.eon.Values;

import java.lang.reflect.Method;

/**
 * Future<T> method(I)
 */
public class AsyncInvoker implements Invoker {
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
        // Get type of parameter first element
        final Class<?> argType = method.getParameterTypes()[Values.IDX];
        // Deserialization from message bus.
        final Class<?> returnType = method.getReturnType();
        // Get T
        final Class<?> tCls = returnType.getComponentType();
        if (Envelop.class == tCls) {
            // Input type is Envelop, input directly
            final Future<Envelop> result = Instance.invoke(proxy, method.getName(), envelop);
            result.setHandler(item -> message.reply(item.result()));
        } else {
            final Object reference = envelop.data();
            final Object arguments = Jackson.deserialize(Types.toString(reference), argType);
            final Future tResult = Instance.invoke(proxy, method.getName(), arguments);
            tResult.setHandler(Ux.toHandler(message));
        }
    }
}
