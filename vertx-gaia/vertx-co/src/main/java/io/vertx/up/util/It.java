package io.vertx.up.util;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class It {
    static java.util.stream.Stream<JsonObject> itJArray(final JsonArray array) {
        return array.stream().filter(item -> item instanceof JsonObject).map(item -> (JsonObject) item);
    }

    static java.util.stream.Stream<String> itJString(final JsonArray array) {
        return array.stream().filter(item -> item instanceof String).map(item -> (String) item);
    }
}
