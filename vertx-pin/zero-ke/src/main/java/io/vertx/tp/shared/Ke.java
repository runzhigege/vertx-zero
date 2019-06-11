package io.vertx.tp.shared;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.function.Function;

public class Ke {
    /*
     * Read jooq configuration database name `catalog`
     */
    public static String getDatabase() {
        return KeTool.getDatabase();
    }

    /*
     * Process image field.
     */
    public static Function<JsonObject, Future<JsonObject>> image(final String field) {
        return KeImage.image(field);
    }
}
