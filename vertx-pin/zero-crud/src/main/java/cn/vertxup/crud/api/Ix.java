package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.up.unity.Ux;
import io.vertx.up.commune.Envelop;

interface Http {
    /* 201 */
    static <T> Future<Envelop> success201(final T entity) {
        return Ux.toFuture(Envelop.success(entity, HttpStatusCode.CREATED));
    }

    /* 200 */
    static <T> Future<Envelop> success200(final T entity) {
        return Ux.toFuture(Envelop.success(entity));
    }

    /* 204 */
    static <T> Future<Envelop> success204(final T entity) {
        return Ux.toFuture(Envelop.success(entity, HttpStatusCode.NO_CONTENT));
    }
}
