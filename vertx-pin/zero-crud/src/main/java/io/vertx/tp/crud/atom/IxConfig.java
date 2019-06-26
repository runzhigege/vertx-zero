package io.vertx.tp.crud.atom;

import com.fasterxml.jackson.databind.JsonArrayDeserializer;
import com.fasterxml.jackson.databind.JsonArraySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonArray;

import java.io.Serializable;

public class IxConfig implements Serializable {
    @JsonSerialize(using = JsonArraySerializer.class)
    @JsonDeserialize(using = JsonArrayDeserializer.class)
    private transient JsonArray patterns;

    public JsonArray getPatterns() {
        return this.patterns;
    }

    public void setPatterns(final JsonArray patterns) {
        this.patterns = patterns;
    }

    @Override
    public String toString() {
        return "IxConfig{" +
                "patterns=" + this.patterns +
                '}';
    }
}
