package io.vertx.up.commune.config;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.SourceType;
import io.vertx.up.util.Ut;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * Assist / Category / Tabular
 * 1) Category ->
 * -- Read categories by type and to `key = JsonArray`
 * 2) Tabular ->
 * -- Read tabular by type and to `key = JsonArray`
 * 3) Assist ->
 * A little complex
 */
public class AdminicleSource implements Serializable {
    /*
     * SourceType of current source definition
     */
    private final transient SourceType source;
    private final transient Set<String> types = new HashSet<>();
    /*
     * Another source of ASSIST here
     */
    private transient String key;
    private transient Class<?> component;

    AdminicleSource(final JsonObject definition) {
        /*
         * Source normalize for `source type`
         */
        final String source = definition.getString("source");
        this.source = Ut.toEnum(() -> source, SourceType.class, SourceType.NONE);
        /*
         * Different definition for
         * 1) CATEGORY / TABULAR
         */
        if (SourceType.CATEGORY == this.source || SourceType.TABULAR == this.source) {
            final JsonArray typeJson = definition.getJsonArray("types");
            if (Objects.nonNull(typeJson)) {
                typeJson.stream().filter(Objects::nonNull)
                        .map(item -> (String) item)
                        .forEach(this.types::add);
            }
        } else if (SourceType.ASSIST == this.source) {
            this.key = definition.getString("key");
            final String className = definition.getString("component");
            if (Ut.notNil(className)) {
                this.component = Ut.clazz(className);
            }
        }
    }

    public SourceType getSourceType() {
        return this.source;
    }

    public Set<String> getTypes() {
        return this.types;
    }

    public String getKey() {
        return this.key;
    }

    public <T> T getPlugin() {
        return Ut.singleton(this.component);
    }
}
