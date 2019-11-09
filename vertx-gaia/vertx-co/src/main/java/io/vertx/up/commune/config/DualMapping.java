package io.vertx.up.commune.config;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.MappingMode;
import io.vertx.up.util.Ut;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/*
 * [Data Structure]
 * 1) mapping stored `from -> to`
 * 2) revert stored `to -> from`
 * This data structure will store two mappings between configuration file here.
 * It could be used in some places to process ( ----> / <---- )
 */
public class DualMapping implements Serializable {

    private final transient ConcurrentMap<String, String> vector =
            new ConcurrentHashMap<>();
    private final transient ConcurrentMap<String, String> revert =
            new ConcurrentHashMap<>();
    private transient MappingMode mode = MappingMode.NONE;
    private transient Class<?> component;

    public DualMapping() {
    }

    public DualMapping(final JsonObject input) {
        this.init(input);
    }

    public DualMapping init(final JsonObject input) {
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
        return this;
    }

    public MappingMode getMode() {
        return this.mode;
    }

    public Class<?> getComponent() {
        return this.component;
    }

    public DualMapping bind(final MappingMode mode) {
        this.mode = mode;
        return this;
    }

    public DualMapping bind(final Class<?> component) {
        this.component = component;
        return this;
    }

    public boolean valid() {
        return MappingMode.NONE != this.mode;
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
        return "Diode{" +
                "vector=" + this.vector +
                ", revert=" + this.revert +
                '}';
    }
}
