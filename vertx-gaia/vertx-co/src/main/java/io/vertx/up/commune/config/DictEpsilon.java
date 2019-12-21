package io.vertx.up.commune.config;

import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.Json;
import io.vertx.up.util.Ut;

import java.io.Serializable;

public class DictEpsilon implements Serializable, Json {

    private transient String source;
    private transient String in;
    private transient String out;

    public String getSource() {
        return this.source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getIn() {
        return this.in;
    }

    public void setIn(final String in) {
        this.in = in;
    }

    public String getOut() {
        return this.out;
    }

    public void setOut(final String out) {
        this.out = out;
    }

    @Override
    public JsonObject toJson() {
        return Ut.serializeJson(this);
    }

    @Override
    public void fromJson(final JsonObject json) {
        if (Ut.notNil(json)) {
            this.source = json.getString("source");
            this.in = json.getString("in");
            this.out = json.getString("out");
        }
    }

    public boolean isValid() {
        return Ut.notNil(this.in) && Ut.notNil(this.out) && Ut.notNil(this.source);
    }
}
