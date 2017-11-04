package com.vie.hoc;

import com.vie.fun.error.JeSupplier;
import com.vie.hors.ZeroException;

import java.util.Arrays;

public class FnFail {

    public static <T> T exec(final JeSupplier<T> supplier,
                             final Object... input) {
        if (0 == input.length) {
            return null;
        }

        T ret = null;
        try {
            final boolean match = Arrays.stream(input).allMatch(FnNil::not);
            if (match) {
                ret = supplier.get();
            }
        } catch (final ZeroException ex) {

        } catch (final Exception ex) {

        }
        return ret;
    }
}
