package io.zero.epic.fn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Supplier;

final class Pond {
    private Pond() {
    }

    /**
     * Memory cache pool implemented by ConcurrentMap( k = v ) instead of create new each time
     *
     * @param pool   Memory concurrent hash map
     * @param key    Input key for cache
     * @param poolFn Supplier of value when create new ( If not in cache )
     * @param <K>    key type
     * @param <V>    value type
     * @return Get or Created V for value
     */
    static <K, V> V exec(final ConcurrentMap<K, V> pool,
                         final K key,
                         final Supplier<V> poolFn) {
        V reference = pool.get(key);
        if (null == reference) {
            reference = poolFn.get();
            if (null != reference) {
                pool.put(key, reference);
            }
        }
        return reference;
    }

    /**
     * Group function
     *
     * @param object  The collection that want to be grouped.
     * @param keyFn   key function ( e -> key )
     * @param valueFn value function ( e -> value )
     * @param <K>     key type
     * @param <V>     value type
     * @param <E>     element type
     * @return Grouped result
     */
    static <K, V, E> ConcurrentMap<K, List<V>> group(final Collection<E> object, final Function<E, K> keyFn, final Function<E, V> valueFn) {
        final ConcurrentMap<K, List<V>> ret = new ConcurrentHashMap<>();
        if (0 < object.size()) {
            for (final E item : object) {
                if (null != item) {
                    final K key = keyFn.apply(item);
                    if (null != key) {
                        // Extract List
                        List<V> reference = null;
                        if (ret.containsKey(key)) {
                            reference = ret.get(key);
                        }
                        // Double check
                        if (null == reference) {
                            reference = new ArrayList<>();
                        }
                        final V value = valueFn.apply(item);
                        if (null != value) {
                            reference.add(value);
                        }
                        // Replace finally
                        ret.put(key, reference);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Zipper
     *
     * @param object The collection that will be zipper
     * @param <K>    key type
     * @param <V>    value type
     * @param <E>    element type
     * @return returned function
     */
    static <K, V, E> ConcurrentMap<K, V> zipper(final Collection<E> object, final Function<E, K> keyFn, final Function<E, V> valueFn) {
        final ConcurrentMap<K, V> ret = new ConcurrentHashMap<>();
        if (0 < object.size()) {
            for (final E item : object) {
                if (null != item) {
                    final K key = keyFn.apply(item);
                    final V value = valueFn.apply(item);
                    if (null != key && null != value) {
                        ret.put(key, value);
                    }
                }
            }
        }
        return ret;
    }
}
