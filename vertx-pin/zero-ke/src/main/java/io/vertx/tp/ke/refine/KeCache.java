package io.vertx.tp.ke.refine;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Session;
import io.vertx.tp.error._409SessionConflictException;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.plugin.session.SessionClient;
import io.vertx.up.plugin.session.SessionInfix;
import io.vertx.zero.epic.Ut;

import java.util.Objects;
import java.util.function.BiFunction;

/*
 * Key generated for uniform platform
 */
class KeCache {

    private static final SessionClient CLIENT = SessionInfix.getClient();

    static String keySession(final String method, final String uri) {
        return "session-" + method + ":" + uri;
    }

    static String keyEnvelop(final Envelop envelop, final BiFunction<String, String, String> executor) {
        final User user = envelop.user();
        if (Objects.nonNull(user)) {
            final JsonObject principle = user.principal();
            if (!Ut.isNil(principle)) {
                /*
                 * Process for metadata in principle here
                 */
                final JsonObject metadata = principle.getJsonObject(KeField.METADATA);
                final String uri = metadata.getString(KeField.URI_REQUEST);
                final String method = metadata.getString(KeField.URI);
                return executor.apply(method, uri);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    static String keyAuthorized(final String method, final String uri) {
        return "authorized-" + method + ":" + uri;
    }

    /*
     * Session Data Get
     */
    static Future<Session> session(final String id) {
        return CLIENT.get(id).compose(session -> {
            if (Objects.nonNull(session) && !session.isDestroyed()) {
                return Future.succeededFuture(session);
            } else {
                final WebException error = new _409SessionConflictException(KeCache.class, id);
                return Future.failedFuture(error);
            }
        });
    }

    static <T> Future<T> session(final Session session, final String sessionKey, final String dataKey, final T value) {
        /* Data Get */
        final Buffer storedBuffer = session.get(sessionKey);
        /* Updated Projection */
        if (Objects.nonNull(storedBuffer)) {
            final JsonObject storedData = storedBuffer.toJsonObject();
            storedData.put(dataKey, value);
            session.put(sessionKey, storedData.toBuffer());
        }
        return Ux.toFuture(value);
    }
}
