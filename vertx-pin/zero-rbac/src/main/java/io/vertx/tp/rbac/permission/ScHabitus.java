package io.vertx.tp.rbac.permission;

import io.vertx.up.fn.Fn;
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
public class ScHabitus {
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
        this.pool = Ux.Pool.on(pool);
    }

    static ScHabitus get(final String habitus) {
        return Fn.pool(POOLS, habitus, () -> new ScHabitus(habitus));
    }
}
