package io.vertx.up.util;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Mapping data to List / JsonArray here
 * [],[],[],[]
 * Mapped by field here for different usage
 */
class Epsilon {

    static Set<String> mapString(final JsonArray array, final String field) {
        Set<String> set = new HashSet<>();
        if (Objects.nonNull(array)) {
            set = array.stream()
                    .filter(item -> item instanceof JsonObject)
                    .map(item -> (JsonObject) item)
                    .filter(item -> item.getValue(field) instanceof String)
                    .map(item -> item.getString(field))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }
        return set;
    }
}
