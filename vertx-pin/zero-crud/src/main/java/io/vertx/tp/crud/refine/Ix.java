package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;

import java.util.function.Function;

public class Ix {

    public static IxIn create(final Class<?> clazz) {
        return IxIn.create(clazz);
    }

    public static Function<UxJooq, Future<JsonObject>> search(final JsonObject filters, final IxConfig config) {
        return IxFn.search(filters, config);
    }

    public static Future<JsonObject> unique(final JsonObject result) {
        return IxData.unique(result);
    }

    public static boolean isExist(final JsonObject result) {
        return IxData.isExist(result);
    }

    public static void audit(final JsonObject auditor, final JsonObject config, final String userId) {
        IxData.audit(auditor, config, userId);
    }

    public static <T> T entity(final JsonObject data, final IxConfig config) {
        return IxData.entity(data, config);
    }

    public static <T> Future<T> entityAsync(final JsonObject data, final IxConfig config) {
        final T reference = entity(data, config);
        return Ux.toFuture(reference);
    }
}
