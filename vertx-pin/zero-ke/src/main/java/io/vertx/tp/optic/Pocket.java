package io.vertx.tp.optic;

import io.vertx.tp.ke.init.KePin;
import io.vertx.tp.ke.tool.Ke;
import io.zero.epic.Ut;

/*
 * T seeking in the environment of kernel.
 */
public class Pocket {
    /*
     * Lookup interface
     */
    public static <T> T lookup(final Class<?> clazz) {
        /*
         * Impl Class
         */
        final Class<?> implCls = KePin.get(clazz);
        return Ke.generate(implCls, () -> Ut.singleton(implCls));
    }
}
