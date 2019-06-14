package io.vertx.tp.rbac.refine;

import io.vertx.core.Future;
import io.vertx.ext.web.Session;
import io.vertx.tp.error._409SessionConflictException;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.tp.rbac.profile.ScSession;
import io.vertx.up.aiki.Ux;
import io.vertx.up.exception.WebException;
import io.vertx.up.plugin.shared.SessionClient;
import io.vertx.up.plugin.shared.SessionInfix;

import java.util.Objects;

/*
 * Data Pool ( Pond ) for Cache
 * There are some pools
 * 1) name = POOL_CODE ( Default Value )
 *      Desc: Authorization cache pool for oauth logging workflow.
 *      1.1. The data existing inner 30s ( configured by `codeExpired` )
 *      1.2. The name `POOL_CODE` could be configured by `codePool`
 *      1.3. The authorization code length could be configured by `codeLength`
 *      1.4. Method: `getCode/putCode`
 * 2)
 */
class ScCache {

    private static final ScConfig CONFIG = ScPin.getConfig();
    private static final SessionClient CLIENT = SessionInfix.getClient();

    /*
     * Pool configured default parameters
     * - codePool
     */
    @SuppressWarnings("all")
    static <V> Future<V> code(final String key) {
        final String codePool = CONFIG.getCodePool();
        return Ux.Pool.on(codePool).remove(key)
                .compose(value -> Ux.toFuture((V) value.getValue()));
    }

    /*
     * Pool configured default parameters
     * - codePool
     * - codeExpired
     */
    static <V> Future<V> code(final String key, final V value) {
        final String codePool = CONFIG.getCodePool();
        final Integer codeExpired = CONFIG.getCodeExpired();
        return Ux.Pool.on(codePool).put(key, value, codeExpired)
                .compose(item -> Ux.toFuture(item.getValue()));
    }

    /*
     * Get Session by id
     */
    static Future<Session> session(final String id) {
        return CLIENT.get(id).compose(session -> {
            if (Objects.nonNull(session) && !session.isDestroyed()) {
                return Future.succeededFuture(session);
            } else {
                final WebException error = new _409SessionConflictException(ScSession.class, id);
                return Future.failedFuture(error);
            }
        });
    }

    /*
     * Pool configured default parameters
     * - permissionPool
     */
    static <V> Future<V> permission(final String key) {
        final String permissionPool = CONFIG.getPermissionPool();
        return Ux.Pool.on(permissionPool).get(key);
    }

    /*
     * Pool configured default parameters
     * - permissionPool
     */
    static <V> Future<V> permission(final String key, final V value) {
        final String permissionPool = CONFIG.getPermissionPool();
        return Ux.Pool.on(permissionPool).put(key, value)
                .compose(item -> Ux.toFuture(item.getValue()));
    }
}
