package com.vie.hoc;

import com.vie.hors.ZeroRunException;

import java.util.function.Function;
import java.util.function.Supplier;

public class HTry {
    /**
     * Special try catch for throwable
     *
     * @param supplier
     * @param fnExp
     * @param <T>
     * @return
     */
    public static <T> T exec(final Supplier<T> supplier,
                             final Function<Throwable, ZeroRunException> fnExp) {
        final T ret;
        try {
            ret = supplier.get();
        } catch (final Throwable ex) {
            throw fnExp.apply(ex);
        }
        return ret;
    }
}
