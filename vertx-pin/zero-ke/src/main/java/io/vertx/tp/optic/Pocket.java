package io.vertx.tp.optic;

import io.vertx.tp.error._501ChannelNotImplementException;
import io.vertx.tp.ke.init.KePin;
import io.vertx.tp.optic.atom.Income;
import io.vertx.tp.optic.atom.Lexeme;
import io.vertx.up.util.Ut;

import java.util.Objects;

/*
 * T seeking in the environment of kernel.
 */
public class Pocket {
    /*
     * Lookup interface
     */
    public static <T> T lookup(final Class<?> clazz) {
        /*
         * Get lexeme reference here.
         */
        final Lexeme lexeme = KePin.get(clazz);
        if (Objects.isNull(lexeme)) {
            /*
             * Null pointer
             */
            return null;
        } else {
            /*
             * Implementation pointer
             */
            final Class<?> implCls = lexeme.getImplCls();
            return Ut.singleton(implCls);
        }
    }

    public static <T> T lookup(final Class<?> clazz, final Class<?> callee) {
        final T ret;
        try {
            ret = lookup(clazz);
        } catch (final Throwable ex) {
            /*
             * This code executed when construct clazz here
             * 1) Class Not Found
             * 2) Access issue
             * 3) Invalid JVM spec.
             */
            ex.printStackTrace();
            throw new _501ChannelNotImplementException(callee, clazz);
        }
        if (Objects.isNull(ret)) {
            /*
             * Finally, the interfaceCls is not created successfully
             */
            throw new _501ChannelNotImplementException(callee, clazz);
        }
        return ret;
    }

    public static Income income(final Class<?> clazz, final Object... args) {
        final Income income = Income.in(clazz);
        for (final Object arg : args) {
            income.in(arg);
        }
        return income;
    }
}
