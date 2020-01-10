package io.vertx.up.util;

import io.vertx.core.json.JsonArray;

class Define {

    static JsonArray sureJArray(final JsonArray array) {
        if (Ut.isNil(array)) {
            return new JsonArray();
        } else {
            return array;
        }
    }
}
