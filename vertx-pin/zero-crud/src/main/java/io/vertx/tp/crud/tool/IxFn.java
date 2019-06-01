package io.vertx.tp.crud.tool;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._404ModuleMissingException;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.exception.WebException;
import io.zero.epic.Ut;

import java.util.function.Function;

class IxFn {
    private static <T> Function<T, Future<JsonObject>> toSingle(final String module) {
        final String pojo = IxDao.get(module).getPojo();
        if (Ut.isNil(pojo)) {
            return Ux::fnJObject;
        } else {
            return Ux.fnJObject(pojo);
        }
    }

    static <T> Future<JsonObject> toSingle(final String module,
                                           final Function<UxJooq, Future<T>> actuator) {
        try {
            final UxJooq dao = IxDao.getDao(module);
            if (null == dao) {
                return Future.failedFuture(new _404ModuleMissingException(IxDao.class, module));
            } else {
                return actuator.apply(dao).compose(toSingle(module));
            }
        } catch (final WebException ex) {
            return Future.failedFuture(ex);
        }
    }

    static Future<Boolean> toBoolean(final String module,
                                     final Function<UxJooq, Future<Boolean>> actuator) {
        try {
            final UxJooq dao = IxDao.getDao(module);
            if (null == dao) {
                return Future.failedFuture(new _404ModuleMissingException(IxDao.class, module));
            } else {
                return actuator.apply(dao).compose(Ux::toFuture);
            }
        } catch (final WebException ex) {
            return Future.failedFuture(ex);
        }
    }
}
