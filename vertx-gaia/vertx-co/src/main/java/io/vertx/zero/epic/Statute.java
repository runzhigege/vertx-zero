package io.vertx.zero.epic;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.vertx.zero.epic.fn.Fn;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Do some specification statute operations
 */
final class Statute {

    private static final Annal LOGGER = Annal.get(Statute.class);

    private Statute() {
    }

    /**
     * @param list     The target list
     * @param fnFilter the filter for list search.
     * @param <T>      The generic type of list element.
     * @return Found type for target generic type.
     */
    static <T> T find(final List<T> list, final Predicate<T> fnFilter) {
        return Fn.getNull(() -> {
            final List<T> filtered = list.stream().filter(fnFilter).collect(Collectors.toList());
            return Fn.getSemi(filtered.isEmpty(), LOGGER,
                    () -> null,
                    () -> filtered.get(Values.IDX));
        }, list, fnFilter);
    }

    static <K, T, V> ConcurrentMap<K, V> reduce(final ConcurrentMap<K, T> from, final ConcurrentMap<T, V> to) {
        final ConcurrentMap<K, V> result = new ConcurrentHashMap<>();
        from.forEach((key, middle) -> {
            final V value = to.get(middle);
            if (null != value) {
                result.put(key, value);
            }
        });
        return result;
    }

    static <K, V> ConcurrentMap<K, V> reduce(final Set<K> from, final ConcurrentMap<K, V> to) {
        final ConcurrentMap<K, V> result = new ConcurrentHashMap<>();
        from.forEach((key) -> {
            final V value = to.get(key);
            if (null != value) {
                result.put(key, value);
            }
        });
        return result;
    }

    static <F, T> ConcurrentMap<F, T> zipper(final List<F> keys, final List<T> values) {
        final ConcurrentMap<F, T> result = new ConcurrentHashMap<>();
        Ut.itList(keys, (key, index) -> {
            final T value = getEnsure(values, index);
            // Ignore for null element
            if (null != key && null != value) {
                result.put(key, value);
            }
        });
        return result;
    }

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

    static JsonObject zipper(final JsonArray array, final String field) {
        final JsonObject grouped = new JsonObject();
        array.stream().map(item -> (JsonObject) item)
                .filter(Objects::nonNull)
                .filter(item -> Objects.nonNull(item.getValue(field)))
                .forEach(item -> grouped.put(item.getString(field), item.copy()));
        return grouped;
    }

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

    static <F, S, T> List<T> zipper(final List<F> first, final List<S> second, final BiFunction<F, S, T> function) {
        final List<T> result = new ArrayList<>();
        Ut.itList(first, (key, index) -> {
            final S value = getEnsure(second, index);
            final T merged = function.apply(key, value);
            if (null != merged) {
                result.add(merged);
            }
        });
        return result;
    }

    private static <T> T getEnsure(final List<T> list, final int index) {
        return (0 <= index) && (index < list.size()) ? list.get(index) : null;
    }
}
