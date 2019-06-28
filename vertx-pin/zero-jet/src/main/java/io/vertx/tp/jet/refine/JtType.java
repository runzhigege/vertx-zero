package io.vertx.tp.jet.refine;

import io.vertx.core.AbstractVerticle;
import io.vertx.tp.error._500ConsumerSpecException;
import io.vertx.tp.error._500WorkerSpecException;
import io.vertx.tp.jet.cv.JtComponent;
import io.vertx.tp.jet.uca.consume.JtConsumer;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.function.Supplier;

class JtType {

    static Class<?> toWorker(final Supplier<String> supplier) {
        final String workerStr = supplier.get();
        final Class<?> clazz = Ut.clazz(workerStr, JtComponent.COMPONENT_DEFAULT_WORKER);
        Fn.out(AbstractVerticle.class != clazz.getSuperclass(), _500WorkerSpecException.class, JtRoute.class, clazz);
        return clazz;
    }

    static Class<?> toConsumer(final Supplier<String> supplier) {
        final String consumerStr = supplier.get();
        final Class<?> clazz = Ut.clazz(consumerStr, JtComponent.COMPONENT_DEFAULT_CONSUMER);
        Fn.out(!Ut.isImplement(clazz, JtConsumer.class), _500ConsumerSpecException.class, JtRoute.class, clazz);
        return clazz;
    }
}
