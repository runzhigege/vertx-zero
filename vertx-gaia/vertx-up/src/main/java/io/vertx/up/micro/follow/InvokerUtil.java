package io.vertx.up.micro.follow;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroSerializer;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.AsyncSignatureException;
import io.vertx.zero.exception.WorkerArgumentException;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * Tool for invoker do shared works.
 */
@SuppressWarnings("unused")
public class InvokerUtil {
    private static final Annal LOGGER = Annal.get(InvokerUtil.class);

    /**
     * Whether this method is void
     *
     * @param method checked method
     * @return checked result
     */
    static boolean isVoid(final Method method) {
        final Class<?> returnType = method.getReturnType();
        return void.class == returnType || Void.class == returnType;
    }

    /**
     * Arguments verification
     * Public for replacing duplicated code
     *
     * @param method checked method.
     * @param target checked class
     */
    public static void verifyArgs(final Method method,
                                  final Class<?> target) {

        // 1. Ensure method length
        final Class<?>[] params = method.getParameterTypes();
        final Annal logger = Annal.get(target);
        // 2. The parameters
        Fn.outUp(Values.ZERO == params.length,
                logger, WorkerArgumentException.class,
                target, method);
    }

    static void verify(
            final boolean condition,
            final Class<?> returnType,
            final Class<?> paramType,
            final Class<?> target) {
        final Annal logger = Annal.get(target);
        Fn.outUp(condition, logger,
                AsyncSignatureException.class, target,
                returnType.getName(), paramType.getName());
    }

    private static Object getValue(final Class<?> type,
                                   final Envelop envelop,
                                   final Supplier<Object> defaultSupplier) {
        // Multi calling for Session type
        final Object value;
        if (Session.class == type) {
            /*
             * RBAC required ( When Authenticate )
             * 1) Provide username / password to get data from remote server.
             * 2) Request temp authorization code ( Required Session ).
             */
            value = envelop.getSession();
        } else {
            value = defaultSupplier.get();
            final Object argument = null == value ? null : ZeroSerializer.getValue(type, value.toString());
        }
        return value;
    }

    static Object invokeMulti(final Object proxy,
                              final Method method,
                              final Envelop envelop) {
        // One type dynamic here
        final Object reference = envelop.data();
        // Non Direct
        final Object[] arguments = new Object[method.getParameterCount()];
        final JsonObject json = (JsonObject) reference;
        final Class<?>[] types = method.getParameterTypes();
        for (int idx = 0; idx < types.length; idx++) {
            // Multi calling for Session type
            final Class<?> type = types[idx];
            final int current = idx;
            arguments[idx] = getValue(type, envelop, () -> json.getValue(String.valueOf(current)));
        }
        return Ut.invoke(proxy, method.getName(), arguments);
    }

    static Object invokeSingle(final Object proxy,
                               final Method method,
                               final Envelop envelop) {
        final Class<?> argType = method.getParameterTypes()[Values.IDX];
        // One type dynamic here
        final Object reference = envelop.data();
        // Non Direct
        Object parameters = reference;
        if (JsonObject.class == reference.getClass()) {
            final JsonObject json = (JsonObject) reference;
            if (isInterface(json)) {
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


    private static boolean isInterface(final JsonObject json) {
        final long count = json.fieldNames().stream().filter(Ut::isInteger)
                .count();
        // All json keys are numbers
        LOGGER.info("[ ZERO ] ( isInterface Mode ) Parameter count: {0}, json: {1}", count, json.encode());
        return count == json.fieldNames().size();
    }
}
