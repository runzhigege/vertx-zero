package com.vie.util;

/**
 * Two workflow
 */
public class If {
    /**
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T exec(final T... t) {
        if (0 < t.length) {
            return t[0];
        } else {
            return null;
        }
    }
}
