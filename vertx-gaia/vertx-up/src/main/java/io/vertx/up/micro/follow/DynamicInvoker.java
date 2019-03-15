package io.vertx.up.micro.follow;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.up.web.ZeroSerializer;
import io.vertx.zero.eon.Values;
import io.zero.epic.Ut;

import java.lang.reflect.Method;

public class DynamicInvoker extends AbstractInvoker {

    @Override
    public void ensure(final Class<?> returnType, final Class<?> paramCls) {
        // Verify
        final boolean valid =
                (void.class != returnType && Void.class != returnType);
        InvokerUtil.verify(!valid, returnType, paramCls, this.getClass());
    }

    @Override
    public void invoke(final Object proxy,
                       final Method method,
                       final Message<Envelop> message) {
        final Envelop envelop = message.body();
        this.getLogger().info(Info.MSG_FUTURE, this.getClass(), method.getReturnType(), false);
        final Envelop returnValue = this.executeMethod(proxy, method, envelop);
        message.reply(returnValue);
    }

    private Envelop executeMethod(final Object proxy,
                                  final Method method,
                                  final Envelop envelop) {
        final Object returnValue;
        final Class<?> argType = method.getParameterTypes()[Values.IDX];
        if (Values.ONE == method.getParameterCount()) {
            if (Envelop.class == argType) {
                // Input type is Envelop, input directly
                returnValue = Ut.invoke(proxy, method.getName(), envelop);
            } else {
                // One type dynamic here
                returnValue = this.invokeSingle(proxy, method, envelop);
            }
        } else {
            // Multi parameter dynamic here
            returnValue = this.invokeMulti(proxy, method, envelop);
        }
        // Return type must not be Envelop because top layer has been finished checking.
        return Envelop.success(returnValue);
    }

    @Override
    public void next(final Object proxy,
                     final Method method,
                     final Message<Envelop> message,
                     final Vertx vertx) {
        final Envelop envelop = message.body();
        this.getLogger().info(Info.MSG_FUTURE, this.getClass(), method.getReturnType(), true);
        final Envelop returnValue = this.executeMethod(proxy, method, envelop);
        this.nextEnvelop(vertx, method, returnValue)
                .setHandler(Ux.toHandler(message));
    }

    /**
     * Invoke with one parameters
     */
    private Object invokeMulti(final Object proxy,
                               final Method method,
                               final Envelop envelop) {
        // One type dynamic here
        final Object reference = envelop.data();
        // Non Direct
        final Object[] arguments = new Object[method.getParameterCount()];
        final JsonObject json = (JsonObject) reference;
        final Class<?>[] types = method.getParameterTypes();
        for (int idx = 0; idx < types.length; idx++) {
            // Value
            final Object value = json.getValue(String.valueOf(idx));
            final Class<?> type = types[idx];
            final Object argument = null == value ? null : ZeroSerializer.getValue(type, value.toString());
            arguments[idx] = argument;
        }
        return Ut.invoke(proxy, method.getName(), arguments);
    }

    private Object invokeSingle(final Object proxy,
                                final Method method,
                                final Envelop envelop) {
        final Class<?> argType = method.getParameterTypes()[Values.IDX];
        // One type dynamic here
        final Object reference = envelop.data();
        // Non Direct
        Object parameters = reference;
        if (JsonObject.class == reference.getClass()) {
            final JsonObject json = (JsonObject) reference;
            if (this.isInterface(json)) {
                // Proxy mode
                if (Values.ONE == json.fieldNames().size()) {
                    // New Mode for direct type
                    parameters = json.getValue("0");
                }
            }
        }
        final Object arguments = ZeroSerializer.getValue(argType, Ut.toString(parameters));
        return Ut.invoke(proxy, method.getName(), arguments);
    }

    private boolean isInterface(final JsonObject json) {
        final long count = json.fieldNames().stream().filter(Ut::isInteger)
                .count();
        // All json keys are numbers
        this.getLogger().info("[ ZERO ] ( isInterface Mode ) Parameter count: {0}, json: {1}", count, json.encode());
        return count == json.fieldNames().size();
    }
}
