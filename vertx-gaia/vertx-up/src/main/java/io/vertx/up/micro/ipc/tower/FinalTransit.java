package io.vertx.up.micro.ipc.tower;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.atom.Envelop;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.up.exception._500RpcMethodInvokeException;
import io.vertx.up.log.Annal;

import java.lang.reflect.Method;

/**
 * The last point for method
 */
public class FinalTransit implements Transit {

    private static final Annal LOGGER = Annal.get(FinalTransit.class);
    private transient Method method;
    private transient Vertx vertx;

    @Override
    public Future<Envelop> async(final Envelop data) {
        // 1. Extract type
        final Object proxy = Instance.singleton(this.method.getDeclaringClass());
        // 2. Async type
        final Future<Envelop> returnValue = Fn.getJvm(
                () -> ReturnTransit.build(() -> this.method.invoke(proxy, data),
                        this.getClass(), this.method),
                this.method
        );
        Fn.outWeb(null == returnValue, LOGGER,
                _500RpcMethodInvokeException.class, this.getClass(), returnValue);
        return returnValue;
    }

    @Override
    public Transit connect(final Method method) {
        this.method = method;
        return this;
    }

    @Override
    public Transit connect(final Vertx vertx) {
        this.vertx = vertx;
        return this;
    }
}
