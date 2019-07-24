package io.vertx.tp.rbac.permission;

import io.vertx.core.Future;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxPool;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * `habitus` mapping here, the structure should be:
 * 1. session id -> habitus ( session id may be upgraded )
 *      when upgraded, the pool should be clear
 * 2. token -> habitus
 */
class ScHabitus {
    private static final Annal LOGGER = Annal.get(ScHabitus.class);
    /*
     * name = HABITUS_CACHE ( Logged User )
     *      2.1. Fixed name for `habitus` storage
     *           The name is that each habitus key should be:
     *               "HABITUS-" + <habitus> + "-SESSION"
     * Each habitus keep a pool
     */
    private static final String POOL_PATTERN = "HABITUS-{0}-POOL";
    private static final ConcurrentMap<String, ScHabitus> POOLS =
            new ConcurrentHashMap<>();
    private final transient UxPool pool;

    private ScHabitus(final String habitus) {
        final String pool = MessageFormat.format(POOL_PATTERN, habitus);
        Sc.infoResource(LOGGER, AuthMsg.POOL_RESOURCE, pool, habitus);
        this.pool = Ux.Pool.on(pool);
    }

    static ScHabitus initialize(final String habitus) {
        return Fn.pool(POOLS, habitus, () -> new ScHabitus(habitus));
    }

    /*
     * Pool should be initialized by pool name above
     */
    <T> Future<T> get(final String dataKey) {
        return pool.get(dataKey);
    }

    <T> Future<T> set(final String dataKey, final T value) {
        return pool.put(dataKey, value)
                .compose(kv -> Ux.toFuture(kv.getValue()));
    }
}
