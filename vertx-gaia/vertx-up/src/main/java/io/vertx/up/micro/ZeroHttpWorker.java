package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.annotations.Worker;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Receipt;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.follow.Invoker;
import io.vertx.up.micro.follow.JetSelector;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.WorkerArgumentException;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Default Http Server worker for different handler.
 * Recommend: Do not modify any workers that vertx zero provided.
 */
@Worker
public class ZeroHttpWorker extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroHttpWorker.class);

    private static final Set<Receipt> RECEIPTS = ZeroAnno.getReceipts();

    private static final ConcurrentMap<Integer, Invoker> INVOKER_MAP =
            new ConcurrentHashMap<>();

    private static final AtomicBoolean LOGGED = new AtomicBoolean(Boolean.FALSE);

    @Override
    public void start() {
        // 1. Get event bus
        final EventBus bus = this.vertx.eventBus();
        // 2. Consume address
        for (final Receipt receipt : RECEIPTS) {
            // 3. Deploy for each type
            final String address = receipt.getAddress();

            // 4. Get target reference and method
            final Object reference = receipt.getProxy();
            final Method method = receipt.getMethod();
            this.verifyArgs(method, this.getClass());

            // length = 1
            final Class<?>[] params = method.getParameterTypes();
            final Class<?> returnType = method.getReturnType();
            final Class<?> paramCls = params[Values.IDX];

            // 6. Invoker select
            final Invoker invoker = JetSelector.select(returnType, paramCls);
            invoker.ensure(returnType, paramCls);
            // 7. Record for different invokers
            INVOKER_MAP.put(receipt.hashCode(), invoker);

            Fn.safeJvm(() -> Fn.safeNull(() -> bus.<Envelop>consumer(address,
                    message -> {
                        if (method.isAnnotationPresent(Ipc.class)) {
                            // Rpc continue replying
                            invoker.next(reference, method, message, this.vertx);
                        } else {
                            // Direct replying
                            invoker.invoke(reference, method, message);
                        }
                    }),
                    address, reference, method), LOGGER
            );
        }
        // Record all the information;
        if (!LOGGED.getAndSet(Boolean.TRUE)) {
            final ConcurrentMap<Class<?>, Set<Integer>> outputMap = new ConcurrentHashMap<>();
            INVOKER_MAP.forEach((key, value) -> {
                if (outputMap.containsKey(value.getClass())) {
                    outputMap.get(value.getClass()).add(key);
                } else {
                    outputMap.put(value.getClass(), new HashSet<>());
                }
            });
            outputMap.forEach((key, value) -> LOGGER.info(Info.MSG_INVOKER, key, Ut.toString(value), String.valueOf(value.size())));
        }
    }

    private void verifyArgs(final Method method,
                            final Class<?> target) {

        // 1. Ensure method length
        final Class<?>[] params = method.getParameterTypes();
        final Annal logger = Annal.get(target);
        // 2. The parameters
        Fn.outUp(Values.ONE != params.length,
                logger, WorkerArgumentException.class,
                target, method);
    }
}
