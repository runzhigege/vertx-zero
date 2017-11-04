package com.vie.hoc;

import com.vie.fun.lang.JcConsumer;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class FnNil {
    /**
     * 如果input为null则不执行，否则执行fnExec
     * fnExec: (t) -> { }
     *
     * @param input
     * @param fnExec
     * @param <T>
     */
    public static <T> void exec(final Consumer<T> fnExec, final Object input) {
        if (null != input) {
            final T cast = (T) input;
            fnExec.accept(cast);
        }
    }

    /**
     * 如果input中每个元素都不为null则执行fnExec
     * fnExec: () -> {}
     *
     * @param fnExec
     * @param input
     */
    public static void exec(final JcConsumer fnExec, final Object... input) {
        if (0 < input.length) {
            final boolean match =
                    Arrays.stream(input).allMatch(FnNil::not);
            if (match) {
                fnExec.exec();
            }
        }
    }

    /**
     * 如果input中每个元素都不为null则执行fnGet
     * fnGet: () -> T
     *
     * @param fnGet
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T get(final Supplier<T> fnGet, final Object... input) {
        T reference = null;
        if (0 < input.length) {
            final boolean match = Arrays.stream(input).allMatch(FnNil::not);
            if (match) {
                reference = fnGet.get();
            }
        }
        return reference;
    }

    /**
     * @param item
     * @return
     */
    public static boolean is(final Object item) {
        return null == item;
    }

    /**
     * @param item
     * @return
     */
    public static boolean not(final Object item) {
        return !is(item);
    }
}
