package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Ix {
    /*
     * IxIn reference
     */
    public static IxIn create(final Class<?> clazz) {
        return IxIn.create(clazz);
    }

    /*
     * search operation
     */
    public static Function<UxJooq, Future<JsonObject>> search(final JsonObject filters, final IxConfig config) {
        return IxFn.search(filters, config);
    }

    /*
     * extract unique record
     */
    public static Future<JsonObject> unique(final JsonObject result) {
        return IxData.unique(result);
    }

    public static Future<JsonArray> list(final JsonObject result) {
        return IxData.list(result);
    }

    /*
     * is existing for result
     */
    public static boolean isExist(final JsonObject result) {
        return IxData.isExist(result);
    }

    /*
     * auditor setting
     */
    public static void audit(final JsonObject auditor, final JsonObject config, final String userId) {
        IxData.audit(auditor, config, userId);
    }

    /*
     * Deserialize to T
     */
    public static <T> Future<T> entityAsync(final JsonObject data, final IxConfig config) {
        final T reference = IxData.entity(data, config);
        return Ux.toFuture(reference);
    }

    @SuppressWarnings("all")
    public static <T> Future<List<T>> entityAsync(final JsonArray data, final IxConfig config) {
        final List<T> list = new ArrayList<>();
        data.stream()
                .filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .map(entity -> (T) IxData.entity(entity, config))
                .forEach(reference -> list.add(reference));
        return Ux.toFuture(list);
    }

    public static Future<JsonObject> inKeys(final JsonArray array, final IxConfig config) {
        return IxQuery.inKeys(array, config);
    }
}
