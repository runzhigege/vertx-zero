package io.vertx.tp.rbac.refine;

import io.vertx.core.Future;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.up.aiki.Ux;

/*
 * Data Pool ( Pond ) for Cache
 * There are some pools
 * 1) name = POOL_OAUTH_CODES ( Default Value )
 *      Desc: Authorization cache pool for oauth logging workflow.
 *      1.1. The data existing inner 30s ( configured by `codeExpired` )
 *      1.2. The name `POOL_OAUTH_CODES` could be configured by `codePool`
 *      1.3. The authorization code length could be configured by `codeLength`
 *      1.4. Method: `getCode/putCode`
 * 2)
 */
class ScSession {

    private static final ScConfig CONFIG = ScPin.getConfig();

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
}
