package io.vertx.tp.crud.atom;

import com.fasterxml.jackson.databind.JsonArrayDeserializer;
import com.fasterxml.jackson.databind.JsonArraySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonArray;

import java.io.Serializable;

public class IxColumn implements Serializable {

    private String actor;

    @JsonSerialize(using = JsonArraySerializer.class)
    @JsonDeserialize(using = JsonArrayDeserializer.class)
    private JsonArray condition;

    public String getActor() {
        return this.actor;
    }

    public void setActor(final String actor) {
        this.actor = actor;
    }

    public JsonArray getCondition() {
        return this.condition;
    }

    public void setCondition(final JsonArray condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "IxColumn{" +
                "actor='" + this.actor + '\'' +
                ", condition=" + this.condition +
                '}';
    }
}
