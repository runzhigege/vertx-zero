package io.zero.epic;

import java.util.HashSet;
import java.util.Set;

/*
 * Set calculation
 */
class Arithmetic {
    /*
     * Set intersect
     */
    static <T> Set<T> intersect(final Set<T> left,
                                final Set<T> right) {
        final Set<T> ret = new HashSet<>(left);
        ret.retainAll(right);
        return ret;
    }

    /*
     * Set union
     */
    static <T> Set<T> union(final Set<T> left,
                            final Set<T> right) {
        final Set<T> ret = new HashSet<>();
        ret.addAll(left);
        ret.addAll(right);
        return ret;
    }

    /*
     * Set diff
     */
    static <T> Set<T> diff(final Set<T> subtrahend,
                           final Set<T> minuend) {
        final Set<T> ret = new HashSet<>(subtrahend);
        ret.removeAll(minuend);
        return ret;
    }
}
