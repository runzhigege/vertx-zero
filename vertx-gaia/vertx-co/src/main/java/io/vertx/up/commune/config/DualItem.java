package io.vertx.up.commune.config;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/*
 * Content for
 * String = String
 */
public class DualItem implements Serializable {
    private final transient ConcurrentMap<String, String> vector =
            new ConcurrentHashMap<>();
    private final transient ConcurrentMap<String, String> revert =
            new ConcurrentHashMap<>();

    DualItem() {
    }

    public DualItem(final JsonObject input) {
        this.init(input);
    }

    void init(final JsonObject input) {
        if (Ut.notNil(input)) {
            input.fieldNames().stream()
                    /* Only stored string value here */
                    .filter(field -> input.getValue(field) instanceof String)
                    .forEach(field -> {
                        final String to = input.getString(field);
                        /* mapping */
                        this.vector.put(field, to);
                        /* revert */
                        this.revert.put(to, field);
                    });
        }
    }

    /*
     * from -> to, to values to String[]
     */
    public String[] to() {
        return this.vector.values().toArray(new String[]{});
    }

    public String[] from() {
        return this.revert.values().toArray(new String[]{});
    }

    public Set<String> to(final JsonArray keys) {
        return keys.stream().filter(item -> item instanceof String)
                .map(item -> (String) item)
                .map(this.vector::get)
                .collect(Collectors.toSet());
    }

    public Set<String> from(final JsonArray keys) {
        return keys.stream().filter(item -> item instanceof String)
                .map(item -> (String) item)
                .map(this.revert::get)
                .collect(Collectors.toSet());
    }

    /*
     * Get value by from key, get to value
     */
    public String to(final String from) {
        return this.vector.get(from);
    }

    public boolean fromKey(final String key) {
        return this.vector.containsKey(key);
    }

    public String from(final String to) {
        return this.revert.get(to);
    }

    public boolean toKey(final String key) {
        return this.revert.containsKey(key);
    }

    /*
     * from -> to, from keys
     */
    public Set<String> keys() {
        return this.vector.keySet();
    }

    public Set<String> values() {
        return new HashSet<>(this.vector.values());
    }

    @Override
    public String toString() {
        return "DualItem{" +
                "vector=" + this.vector +
                ", revert=" + this.revert +
                '}';
    }
}
