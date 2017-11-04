package com.vie.hoc;

import com.vie.fun.error.JeSupplier;
import com.vie.hors.ZeroException;
import com.vie.hors.ZeroRunException;

import java.util.Arrays;

public class HFail {

    public static <T> T exec(final JeSupplier<T> supplier,
                             final Object... input) {
        if (0 == input.length) {
            return null;
        }

        T ret = null;
        try {
            final boolean match = Arrays.stream(input).allMatch(HNull::not);
            if (match) {
                ret = supplier.get();
            }
        } catch (final ZeroException ex) {

        } catch (final ZeroRunException ex) {
            // Throw out customed exception only.
            throw ex;
        } catch (final Exception ex) {

        }
        return ret;
    }
}
