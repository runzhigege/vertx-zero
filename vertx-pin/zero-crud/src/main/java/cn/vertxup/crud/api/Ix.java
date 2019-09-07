package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.up.commune.Envelop;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

interface Http {

    /* 201 */
    static <T> Future<Envelop> success201(final T entity) {
        return Ux.toFuture(Envelop.success(entity, HttpStatusCode.CREATED));
    }

    static <T> Future<Envelop> success201(final T entity, final IxModule config) {
        final String pojo = config.getPojo();
        if (Ut.isNil(pojo)) {
            return success201(entity);
        } else {
            final JsonObject serializedJson = Ux.toJson(entity, pojo);
            return Ux.toFuture(Envelop.success(serializedJson, HttpStatusCode.CREATED));
        }
    }

    /* 200 */
    static <T> Future<Envelop> success200(final T entity) {
        return Ux.toFuture(Envelop.success(entity));
    }

    static <T> Future<Envelop> success200(final T entity, final IxModule config) {
        final String pojo = config.getPojo();
        if (Ut.isNil(pojo)) {
            return success200(entity);
        } else {
            final JsonObject serializedJson = Ux.toJson(entity, pojo);
            return Ux.toFuture(Envelop.success(serializedJson));
        }
    }

    /* 204 */
    static <T> Future<Envelop> success204(final T entity) {
        return Ux.toFuture(Envelop.success(entity, HttpStatusCode.NO_CONTENT));
    }
}
