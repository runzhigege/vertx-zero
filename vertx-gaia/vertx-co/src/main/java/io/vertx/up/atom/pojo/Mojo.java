package io.vertx.up.atom.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Pojo metadata container
 * Meta of Java Object
 */
public class Mojo implements Serializable {

    private static final Annal LOGGER = Annal.get(Mojo.class);
    private static final String TYPE = "type";
    private static final String MAPPING = "mapping";
    @JsonIgnore
    private final ConcurrentMap<String, String> columns = new ConcurrentHashMap<>();
    @JsonProperty(TYPE)
    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> type;
    @JsonProperty(MAPPING)
    private ConcurrentMap<String, String> config = new ConcurrentHashMap<>();

    public Class<?> getType() {
        return this.type;
    }

    public void setType(final Class<?> type) {
        this.type = type;
    }

    public ConcurrentMap<String, String> getOut() {
        // Fix no mapping issue for empty mapping conversion.
        Fn.safeSemi(null == this.config, LOGGER, () -> this.config = new ConcurrentHashMap<>());
        return this.config;
    }

    @SuppressWarnings("all")
    public ConcurrentMap<String, String> getIn() {
        Fn.safeSemi(config.keySet().size() != config.values().size(), LOGGER,
                () -> LOGGER.warn(Info.VALUE_SAME,
                        config.keySet().size(), config.values().size()));
        final ConcurrentMap<String, String> mapper =
                new ConcurrentHashMap<>();
        config.forEach((key, value) -> mapper.put(value, key));
        return mapper;
    }

    public ConcurrentMap<String, String> getInAll() {
        if (this.columns.isEmpty()) {
            this.columns.putAll(this.getIn());
        }
        return this.columns;
    }

    public ConcurrentMap<String, String> getOutAll() {
        final ConcurrentMap<String, String> revert = new ConcurrentHashMap<>();
        if (!this.columns.isEmpty()) {
            this.columns.forEach((key, value) -> revert.put(value, key));
        }
        return revert;
    }

    public Mojo put(final ConcurrentMap<String, String> columns) {
        if (this.columns.isEmpty()) {
            this.columns.putAll(this.getIn());
        }
        if (null != columns && !columns.isEmpty()) {
            this.columns.putAll(columns);
        }
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mojo)) {
            return false;
        }
        final Mojo mojo = (Mojo) o;
        return Objects.equals(this.type, mojo.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.type);
    }
}
