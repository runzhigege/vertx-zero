package com.vie.hoc;

import com.vie.fun.error.JeSupplier;
import com.vie.hors.ZeroRunException;

import java.util.function.Supplier;

public class HBool {
    /**
     * @param condition
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T execTrue(final boolean condition,
                                 final JeSupplier<T> supplier) {
        T ret = null;
        if (condition) {
            try {
                ret = supplier.get();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * @param condition
     * @param supplier
     * @param exp
     * @param <T>
     * @return
     */
    public static <T> T exec(final boolean condition,
                             final Supplier<T> supplier,
                             final ZeroRunException exp) {
        if (condition) {
            return supplier.get();
        } else {
            throw exp;
        }
    }

    /**
     * @param condition
     * @param tSupplier
     * @param fSupplier
     * @param <T>
     * @return
     */
    public static <T> T exec(final boolean condition,
                             final Supplier<T> tSupplier,
                             final Supplier<T> fSupplier) {
        T ret = null;
        if (condition) {
            if (null != tSupplier) {
                ret = tSupplier.get();
            }
        } else {
            if (null != fSupplier) {
                ret = fSupplier.get();
            }
        }
        return ret;
    }
}
