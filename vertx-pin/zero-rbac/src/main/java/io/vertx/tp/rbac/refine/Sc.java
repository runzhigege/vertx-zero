package io.vertx.tp.rbac.refine;

import cn.vertxup.rbac.domain.tables.pojos.OAccessToken;
import cn.vertxup.rbac.domain.tables.pojos.SResource;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.log.Annal;

import java.util.List;

public class Sc {
    /*
     * Log information with input Logger
     */
    public static void infoAuth(final Annal logger, final String pattern, final Object... args) {
        ScLog.infoAuth(logger, pattern, args);
    }

    public static void infoInit(final Annal logger, final String pattern, final Object... args) {
        ScLog.infoInit(logger, pattern, args);
    }

    public static void infoResource(final Annal logger, final String pattern, final Object... args) {
        ScLog.infoResource(logger, pattern, args);
    }

    public static void infoCredit(final Annal logger, final String pattern, final Object... args) {
        ScLog.infoCredit(logger, pattern, args);
    }

    /*
     * Uri normalize and extraction of tool
     */
    public static String uri(final String uri, final String requestUri) {
        return ScPhase.uri(uri, requestUri);
    }

    public static String uri(final RoutingContext context) {
        return ScPhase.uri(context);
    }

    public static JsonObject cacheData(final RoutingContext context) {
        return ScPhase.cacheData(context);
    }

    /*
     * cache information
     * 1. Code: Authorization Code Cache Pool
     *    - get data from code cache
     *    - put data into code cache
     */
    public static <V> Future<V> cacheCode(final String key) {
        return ScCache.code(key);
    }

    public static <V> Future<V> cacheCode(final String key, final V value) {
        return ScCache.code(key, value);
    }

    public static <V> Future<V> cachePermission(final String key) {
        return ScCache.permission(key);
    }

    public static <V> Future<V> cachePermission(final String key, final V value) {
        return ScCache.permission(key, value);
    }

    /*
     * Business logical
     * Generate authorization code based on `configuration.json` file
     * - codeExpired
     * - codeLength
     * - codePool
     */
    public static String generateCode() {
        return ScTool.generateCode();
    }

    public static String generateProfileKey(final SResource resource) {
        return ScTool.generateProfileKey(resource);
    }

    /*
     * Jwt token process
     * 1) Build jwt token response
     * {
     *     access_token: "xxx",
     *     refresh_token: "xxx",
     *     iat: xx
     * }
     * 2) Build OAccessToken object, this object will be stored into database.
     */
    public static JsonObject jwtToken(final JsonObject data) {
        return ScToken.jwtToken(data);
    }

    public static Future<Boolean> jwtToken(final List<OAccessToken> item, final String userId) {
        return ScToken.jwtToken(item, userId);
    }

    public static OAccessToken jwtToken(final JsonObject jwt, final String userKey) {
        return ScToken.jwtToken(jwt, userKey);
    }

    /*
     * Relation query based on input parameters
     * Query `R_X_Y` Where `field = key` ( field belong to X )
     *
     * 1) R_USER_ROLE: Query roles based on user's key
     * 2) R_GROUP_ROLE: Query roles based on group's key
     * 3) R_USER_GROUP: Query groups based on user's key
     */
    public static <T> Future<JsonArray> relation(final String field, final String key, final Class<?> daoCls) {
        return ScFn.<T>relation(field, key, daoCls);
    }

    public static <T> Future<List<T>> composite(final CompositeFuture res) {
        return ScFn.composite(res);
    }
}
