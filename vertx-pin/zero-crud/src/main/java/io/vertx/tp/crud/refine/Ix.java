package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxMeta;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;

import java.util.List;
import java.util.function.Function;

public class Ix {
    // Is --- Checking the result, return boolean
    /*
     * is existing for result
     */
    public static boolean isExist(final JsonObject result) {
        return IxIs.isExist(result);
    }

    // Business Logical
    /*
     * auditor setting
     */
    public static void audit(final JsonObject auditor, final JsonObject config, final String userId) {
        IxFn.audit(auditor, config, userId);
    }

    /*
     * search operation
     */
    public static Function<UxJooq, Future<JsonObject>> search(final JsonObject filters, final IxConfig config) {
        return IxFn.search(filters, config);
    }

    public static Function<UxJooq, Future<JsonObject>> query(final JsonObject filters, final IxConfig config) {
        return IxFn.query(filters, config);
    }

    public static Function<UxJooq, Future<Boolean>> existing(final JsonObject filters, final IxConfig config) {
        return IxFn.existing(filters, config);
    }

    // Atom creation
    /*
     * IxIn reference
     */
    public static IxMeta create(final Class<?> clazz) {
        return IxMeta.create(clazz);
    }

    // Serialization for entity/list
    /*
     * extract unique record
     */
    public static Future<JsonObject> unique(final JsonObject result) {
        return Ux.toFuture(IxSerialize.unique(result));
    }

    public static Future<JsonArray> list(final JsonObject result) {
        return Ux.toFuture(IxSerialize.list(result));
    }

    /*
     * Deserialize to T
     */
    public static <T> Future<T> entityAsync(final JsonObject data, final IxConfig config) {
        final T reference = IxSerialize.entity(data, config);
        return Ux.toFuture(reference);
    }

    @SuppressWarnings("all")
    public static <T> Future<List<T>> entityAsync(final JsonArray data, final IxConfig config) {
        return Ux.toFuture(IxSerialize.entity(data, config));
    }

    public static Future<JsonArray> zipperAsync(final JsonArray from, final JsonArray to, final IxConfig config) {
        return Ux.toFuture(IxSerialize.zipper(from, to, config));
    }

    // Query
    public static Future<JsonObject> inKeys(final JsonArray array, final IxConfig config) {
        return Ux.toFuture(IxQuery.inKeys(array, config));
    }

    public static Future<JsonObject> inColumns(final Envelop envelop, final IxConfig config) {
        return Ux.toFuture(IxQuery.inColumns(envelop, config));
    }

    /*
     * Log
     */
    public static void infoInit(final Annal logger, final String pattern, final Object... args) {
        IxLog.infoInit(logger, pattern, args);
    }

    public static void infoRest(final Annal logger, final String pattern, final Object... args) {
        IxLog.infoRest(logger, pattern, args);
    }

    public static void infoFilters(final Annal logger, final String pattern, final Object... args) {
        IxLog.infoFilters(logger, pattern, args);
    }

    public static void infoVerify(final Annal logger, final String pattern, final Object... args) {
        IxLog.infoVerify(logger, pattern, args);
    }

    public static void infoDao(final Annal logger, final String pattern, final Object... args) {
        IxLog.infoDao(logger, pattern, args);
    }

    public static void errorInit(final Annal logger, final String pattern, final Object... args) {
        IxLog.errorInit(logger, pattern, args);
    }
}
