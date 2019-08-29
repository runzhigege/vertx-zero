package io.vertx.tp.ke.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.commune.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.function.Function;
import java.util.function.Supplier;

public class Ke {

    /*
     * Read jooq configuration database name `catalog`
     */
    public static String getDatabase() {
        return KeTool.getCatalog();
    }

    /*
     * Process image field.
     */
    public static Function<JsonObject, Future<JsonObject>> image(final String field) {
        return KeImage.image(field);
    }

    public static Function<JsonObject, Future<JsonObject>> metadata(final String field) {
        return KeElement.metadata(field);
    }

    public static Function<JsonObject, Future<JsonObject>> metadataArray(final String field) {
        return KeElement.metadataArray(field);
    }

    public static <T> Future<T> poolAsync(final String name, final String key, final Supplier<Future<T>> supplier) {
        return KeTool.poolAsync(name, key, supplier);
    }

    public static void banner(final String module) {
        KeTool.banner(module);
    }

    public static void infoKe(final Annal logger, final String pattern, final Object... args) {
        KeLog.infoKe(logger, pattern, args);
    }

    /*
     * Session key generation
     */
    public static String keySession(final String method, final String uri) {
        return KeCache.keySession(method, uri);
    }

    public static String keyAuthorized(final String method, final String uri) {
        return KeCache.keyAuthorized(method, uri);
    }

    public static String keyHabitus(final Envelop envelop) {
        return KeCache.keyHabitus(envelop);
    }


    /*
     * Get keySession here for current logged user
     */
    public static Future<Session> session(final String id) {
        return KeCache.session(id);
    }

    public static <T> Future<T> session(final Session session, final String sessionKey, final String dataKey, final T value) {
        return KeCache.session(session, sessionKey, dataKey, value);
    }

    /*
     * Result for response
     */
    public interface Result {

        static JsonObject bool(final boolean checked) {
            return KeResult.bool(KeField.RESULT, checked);
        }

        static Future<JsonObject> boolAsync(final boolean checked) {
            return Ux.toFuture(bool(checked));
        }

        static Future<Boolean> boolAsync(final JsonObject checkedJson) {
            return Ux.toFuture(KeResult.bool(checkedJson));
        }

        static Future<JsonObject> jsonAsync(final JsonObject result) {
            return Ux.toFuture(Ut.isNil(result) ? new JsonObject() : result);
        }

        static JsonObject bool(final String key, final boolean checked) {
            return KeResult.bool(key, checked);
        }

        static JsonObject array(final JsonArray array) {
            return KeResult.array(array);
        }
    }
}
