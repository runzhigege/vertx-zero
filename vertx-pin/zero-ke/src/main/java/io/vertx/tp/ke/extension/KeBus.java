package io.vertx.tp.ke.extension;

import io.vertx.tp.error._501ExtensionAbstractException;
import io.vertx.tp.error._501ExtensionInterfaceException;
import io.vertx.tp.ke.extension.jooq.AbstractJewel;
import io.vertx.tp.ke.extension.jooq.Epidemia;
import io.vertx.tp.ke.extension.jooq.EpidemiaMy;
import io.vertx.tp.ke.extension.jooq.Manhunt;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

/*
 * KeBus for extension creation
 */
public class KeBus {
    /*
     * Orbit Generate method
     */
    public static Orbit orbit(final Class<?> clazz) {
        ensureInterface(clazz, Orbit.class);
        return Ut.singleton(clazz);
    }

    public static Epidemia epidemia(final Class<?> clazz) {
        ensure(clazz, AbstractJewel.class, Epidemia.class);
        return Ut.singleton(clazz);
    }

    public static EpidemiaMy epidemiaMy(final Class<?> clazz) {
        ensure(clazz, AbstractJewel.class, EpidemiaMy.class);
        return Ut.singleton(clazz);
    }

    public static Manhunt manhunt(final Class<?> clazz) {
        ensure(clazz, AbstractJewel.class, Manhunt.class);
        return Ut.singleton(clazz);
    }

    /*
     * Double check method to be sure you defined correct class here.
     */
    private static void ensureInterface(final Class<?> clazz, final Class<?> interfaceCls) {
        Fn.outWeb(!Ut.isImplement(clazz, interfaceCls), _501ExtensionInterfaceException.class, KeBus.class,
                clazz.getName(), interfaceCls.getName());
    }

    private static void ensureAbstract(final Class<?> clazz, final Class<?> abstractCls) {
        final Class<?> superClass = clazz.getSuperclass();
        Fn.outWeb(abstractCls != superClass, _501ExtensionAbstractException.class, KeBus.class,
                clazz.getName(), abstractCls.getName());
    }

    private static void ensure(final Class<?> clazz, final Class<?> abstractCls, final Class<?> interfaceCls) {
        /* Parent class check */
        ensureAbstract(clazz, abstractCls);
        /* Interface class check */
        ensureInterface(clazz, interfaceCls);
    }
}
