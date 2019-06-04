package io.vertx.up.eon;

import io.vertx.core.json.JsonObject;
import io.zero.epic.Ut;

/*
 * Json Data Specification
 */
public interface ZJson {
    JsonObject toJson();

    void fromJson(JsonObject json);

    default void fromFile(final String jsonFile) {
        final JsonObject data = Ut.ioJObject(jsonFile);
        this.fromJson(data);
    }
}
