package io.vertx.up.uca.container;

import java.util.Map;
import java.util.Objects;

public final class Kv<K, V> {
    private K key;
    private V value;

    private Kv(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Kv<K, V> create() {
        return new Kv<>(null, null);
    }

    public static <K, V> Kv<K, V> create(final K key,
                                         final V value) {
        return new Kv<>(key, value);
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }

    public void set(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public final boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Map.Entry) {
            final Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Kv{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
