package com.vie.hoc;

import com.vie.fun.lang.JcConsumer;

import java.util.Arrays;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class $Nil {
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
                    Arrays.stream(input).allMatch($Nil::not);
            if (match) {
                fnExec.exec();
            }
        }
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
