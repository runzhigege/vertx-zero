package com.vie.util;

import com.vie.hoc.HFail;
import com.vie.hoc.HNull;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lang
 */
public class StringUtil {

    public static Set<String> split(final String input, final String separator) {
        return HFail.exec(() -> {
            final String[] array = input.split(separator);
            final Set<String> result = new HashSet<>();
            for (final String item : array) {
                HNull.exec(() -> result.add(item.trim().intern()), item);
            }
            return result;
        }, input, separator);
    }

    public static boolean isNil(final String input) {
        return null == input || 0 == input.trim().length() || 0 == input.trim().length();
    }
}
