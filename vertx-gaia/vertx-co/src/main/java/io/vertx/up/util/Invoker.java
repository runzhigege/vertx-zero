package io.vertx.up.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.up.exception.zero.InvokingSpecException;
import io.vertx.up.fn.Actuator;
import io.vertx.up.fn.Fn;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Call interface method by cglib
 */
@SuppressWarnings("unchecked")
final class Invoker {
    private Invoker() {
    }

    static <T> T invokeObject(
            final Object instance,
            final String name,
            final Object... args) {
        return Fn.getNull(() -> {
            final MethodAccess access = MethodAccess.get(instance.getClass());
            // Direct invoke, multi overwrite for unbox/box issue still existing.
            // TODO: Unbox/Box type issue
            Object result;
            try {
                result = access.invoke(instance, name, args);
            } catch (final Throwable ex) {
                ex.printStackTrace();
                // Could not call, re-find the method by index
                // Search method by argument index because could not call directly
                final int index;
                final List<Class<?>> types = new ArrayList<>();
                for (final Object arg : args) {
                    types.add(Ut.toPrimary(arg.getClass()));
                }
                index = access.getIndex(name, types.toArray(new Class<?>[]{}));
                result = access.invoke(instance, index, args);
            }
            final Object ret = result;
            return Fn.getNull(() -> (T) ret, ret);
        }, instance, name);
    }

    static <T> Future<T> invokeAsync(final Object instance,
                                     final Method method,
                                     final Object... args) {
        /*
         * Analyzing method returnType first
         */
        final Class<?> returnType = method.getReturnType();
        final Future<T> future = Future.future();
        try {
            /*
             * Void return for continue calling
             */
            if (void.class == returnType) {
                /*
                 * When void returned, here you must set the last argument to Future<T>
                 * Arguments [] + Future<T> future
                 *
                 * Critical:
                 * -- It means that if you want to return void.class, you must provide
                 *    argument future and let the future as last arguments
                 * -- The basic condition is
                 *    method declared length = args length + 1
                 */
                Fn.out(method.getParameters().length != args.length + 1,
                        InvokingSpecException.class, Invoker.class, method);
                final Object[] arguments = Ut.elementAdd(args, future);
                method.invoke(instance, arguments);
            } else {
                final Object returnValue = method.invoke(instance, args);
                if (Objects.isNull(returnValue)) {
                    /*
                     * Future also null
                     */
                    future.complete(null);
                } else {
                    if (Instance.isMatch(returnType, Future.class)) {
                        /*
                         * Future<T> returned directly,
                         * Connect future -> future
                         */
                        ((Future<T>) returnValue).setHandler(handler -> {
                            if (handler.succeeded()) {
                                future.complete(handler.result());
                            } else {
                                future.fail(handler.cause());
                            }
                        });
                    } else if (Instance.isMatch(returnType, AsyncResult.class)) {
                        /*
                         * AsyncResult
                         */
                        final AsyncResult<T> async = (AsyncResult<T>) returnValue;
                        if (async.succeeded()) {
                            future.complete(async.result());
                        } else {
                            future.fail(async.cause());
                        }
                    } else if (Instance.isMatch(returnType, Handler.class)) {
                        /*
                         * Handler, not testing here.
                         * Connect future to handler
                         */
                        future.setHandler(((Handler<AsyncResult<T>>) returnValue));
                    } else {
                        /*
                         * Sync calling
                         */
                        final T returnT = (T) returnValue;
                        future.complete(returnT);
                    }
                }
            }
        } catch (final Throwable ex) {
            // TODO: DEBUG for JVM
            ex.printStackTrace();
            future.fail(ex);
        }
        return future;
    }

    private static <T> void invokeAsync(final Future<T> future, final Actuator actuator) {
        try {
            actuator.execute();
        } catch (final Throwable ex) {
            future.fail(ex);
        }
    }

    static <T> T invokeInterface(
            final Class<?> interfaceCls,
            final String name,
            final Object... args) {
        final Object delegate = getProxy(interfaceCls);
        return Fn.getJvm(() -> invokeObject(delegate, name, args), delegate);
    }

    static <T> T getProxy(
            final Class<?> interfaceCls
    ) {
        return Fn.getNull(() -> {
            // TODO: Generate interface proxy

            return null;
        }, interfaceCls);
    }
}
