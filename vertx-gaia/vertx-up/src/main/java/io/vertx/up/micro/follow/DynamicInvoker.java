package io.vertx.up.micro.follow;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.tool.mirror.Types;
import io.vertx.zero.eon.Values;

import java.lang.reflect.Method;

public class DynamicInvoker implements Invoker {
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

        // Get type of parameter first element.
        final Class<?> argType = method.getParameterTypes()[Values.IDX];
        // Deserialization from message bus.
        final Object returnValue;
        if (Envelop.class == argType) {
            // Input type is Envelop, input directly
            returnValue = Instance.invoke(proxy, method.getName(), envelop);
        } else {
            final Object reference = envelop.data();
            final Object arguments = Jackson.deserialize(Types.toString(reference), argType);
            returnValue = Instance.invoke(proxy, method.getName(), arguments);
        }
        // Return type must not be Envelop because top layer has been finished checking.
        message.reply(Envelop.success(returnValue));
    }
}
