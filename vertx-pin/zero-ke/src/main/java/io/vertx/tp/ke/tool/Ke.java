package io.vertx.tp.ke.tool;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;

import java.util.function.Function;
import java.util.function.Supplier;

public class Ke {

    /*
     * Read jooq configuration database name `catalog`
     */
    public static String getDatabase() {
        return KeTool.getCatalog();
    }

    public static <T> T generate(final Class<?> clazz, final Supplier<T> supplier) {
        return KeFn.generate(clazz, supplier);
    }

    /*
     * Process image field.
     */
    public static Function<JsonObject, Future<JsonObject>> image(final String field) {
        return KeImage.image(field);
    }

    public static <T> Future<T> poolAsync(final String name, final String key, final Supplier<Future<T>> supplier) {
        return KeTool.poolAsync(name, key, supplier);
    }

    public static void banner(final String module) {
        KeTool.banner(module);
    }

    /*
     * Result for response
     */
    public interface Result {

        static JsonObject bool(final boolean checked) {
            return KeResult.bool(KeField.RESULT, checked);
        }

        static JsonObject bool(final String key, final boolean checked) {
            return KeResult.bool(key, checked);
        }

        static JsonObject array(final JsonArray array) {
            return KeResult.array(array);
        }
    }
}
