package io.vertx.up.micro.follow;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ipc.client.TunnelClient;
import io.vertx.up.web.ZeroSerializer;
import io.vertx.zero.eon.Values;
import io.zero.epic.Ut;

import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * Uniform call TunnelClient to remove duplicated codes
 * Refactor invokder to support Dynamic Invoke
 */
@SuppressWarnings("all")
public abstract class AbstractInvoker implements Invoker {

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }

    /**
     * Future method(JsonObject)
     * Future method(JsonArray)
     */
    protected Future invokeJson(
            final Object proxy,
            final Method method,
            final Envelop envelop) {
        final Object reference = envelop.data();
        final Class<?> argType = method.getParameterTypes()[Values.IDX];
        final Object arguments = Ut.deserialize(Ut.toString(reference), argType);
        return Ut.invoke(proxy, method.getName(), arguments);
    }

    /**
     * R method(T..)
     */
    protected Object invokeInternal(
            final Object proxy,
            final Method method,
            final Envelop envelop
    ) {
        // Return value here.
        Object returnValue;
        final Class<?>[] argTypes = method.getParameterTypes();
        final Class<?> returnType = method.getReturnType();
        if (Values.ONE == method.getParameterCount()) {
            final Class<?> firstArg = argTypes[Values.IDX];
            if (Envelop.class == firstArg) {
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
        return returnValue;
    }

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

    /**
     *
     */
    protected <I> Function<I, Future<Envelop>> nextEnvelop(
            final Vertx vertx,
            final Method method) {
        return item -> this.nextEnvelop(vertx, method, item);
    }

    protected <T> Future<Envelop> nextEnvelop(
            final Vertx vertx,
            final Method method,
            final T result
    ) {
        return TunnelClient.create(this.getClass())
                .connect(vertx)
                .connect(method)
                .send(Ux.to(result));
    }
}
