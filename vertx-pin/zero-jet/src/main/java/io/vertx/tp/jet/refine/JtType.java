package io.vertx.tp.jet.refine;

import cn.vertxup.jet.tables.pojos.IApi;
import io.vertx.core.AbstractVerticle;
import io.vertx.tp.error._500ConsumerSpecException;
import io.vertx.tp.error._500WorkerSpecException;
import io.vertx.tp.jet.atom.JtWorker;
import io.vertx.tp.jet.cv.JtConstant;
import io.vertx.tp.jet.cv.em.WorkerType;
import io.vertx.tp.jet.uca.tunnel.AdaptorChannel;
import io.vertx.tp.optic.jet.JtConsumer;
import io.vertx.up.annotations.Contract;
import io.vertx.up.eon.em.ChannelType;
import io.vertx.up.exception._412ContractFieldException;
import io.vertx.zero.eon.Values;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Supplier;

class JtType {

    private static Class<?> toWorker(final Supplier<String> supplier) {
        final String workerStr = supplier.get();
        final Class<?> clazz = Ut.clazz(workerStr, JtConstant.COMPONENT_DEFAULT_WORKER);
        Fn.out(AbstractVerticle.class != clazz.getSuperclass(), _500WorkerSpecException.class, JtRoute.class, clazz);
        return clazz;
    }

    private static Class<?> toConsumer(final Supplier<String> supplier) {
        final String consumerStr = supplier.get();
        final Class<?> clazz = Ut.clazz(consumerStr, JtConstant.COMPONENT_DEFAULT_CONSUMER);
        Fn.out(!Ut.isImplement(clazz, JtConsumer.class), _500ConsumerSpecException.class, JtRoute.class, clazz);
        return clazz;
    }


    static JtWorker toWorker(final IApi api) {
        final JtWorker worker = new JtWorker();

        /*
         * Worker object instance in current uri here.
         */
        worker.setWorkerAddress(api.getWorkerAddress());
        worker.setWorkerJs(api.getWorkerJs());
        worker.setWorkerType(Ut.toEnum(api::getWorkerType, WorkerType.class, WorkerType.STD));
        worker.setWorkerClass(toWorker(api::getWorkerClass));
        worker.setWorkerConsumer(JtType.toConsumer(api::getWorkerConsumer));
        return worker;
    }

    static Class<?> toChannel(final Supplier<String> supplier, final ChannelType type) {
        final Class<?> clazz;
        if (ChannelType.DEFINE == type) {
            /*
             * User defined channel class
             *  */
            final String channelClass = supplier.get();
            if (Ut.isNil(channelClass)) {
                /*
                 * Adaptor as default channel
                 *  */
                clazz = Pool.CHANNELS.get(ChannelType.ADAPTOR);
            } else {
                /*
                 * User defined channel as selected channel
                 *  */
                clazz = Ut.clazz(channelClass);
            }
        } else {
            /*
             * Here the type should be not "DEFINE", it used `Standard` channel
             * */
            clazz = Pool.CHANNELS.getOrDefault(type, AdaptorChannel.class);
        }
        return clazz;
    }

    static <T> Field toContract(final Class<?> executor, final T instance, final Class<?> fieldType) {
        /*
         * Reflect to set Api reference in target channel here
         * 1) The fields length must be 1
         * 2) The fields length must not be 0
         *  */
        final Field[] fields = Ut.fieldAll(instance, fieldType);
        /*
         * Counter
         */
        final Field[] filtered = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Contract.class))
                .toArray(Field[]::new);
        Fn.out(1 != filtered.length, _412ContractFieldException.class,
                executor, fieldType, instance.getClass(), filtered.length);
        return filtered[Values.IDX];
    }
}
