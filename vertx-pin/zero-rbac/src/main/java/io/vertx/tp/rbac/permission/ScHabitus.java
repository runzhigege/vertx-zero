package io.vertx.tp.rbac.permission;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxPool;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * `habitus` mapping here, the structure should be:
 * 1. session id -> habitus ( session id may be upgraded )
 *      when upgraded, the pool should be clear
 * 2. token -> habitus
 */
public class ScHabitus {
    private static final Annal LOGGER = Annal.get(ScHabitus.class);
    /*
     * name = HABITUS_CACHE ( Logged User )
     *      2.1. Fixed name for `habitus` storage
     *           The name is that each habitus key should be:
     *               "HABITUS-" + <habitus> + "-SESSION"
     * Each habitus keep a pool
     */
    private static final String POOL_HABITUS = "vertx-web.sessions.habitus";
    private static final ConcurrentMap<String, ScHabitus> POOLS =
            new ConcurrentHashMap<>();
    private final transient UxPool pool;
    private final transient String habitus;

    private ScHabitus(final String habitus) {
        Sc.infoResource(LOGGER, AuthMsg.POOL_RESOURCE, POOL_HABITUS, habitus);
        this.habitus = habitus;
        pool = Ux.Pool.on(POOL_HABITUS);
    }

    public static ScHabitus initialize(final String habitus) {
        return Fn.pool(POOLS, habitus, () -> new ScHabitus(habitus));
    }

    /*
     * Pool should be initialized by pool name above
     */
    @SuppressWarnings("unchecked")
    public <T> Future<T> get(final String dataKey) {
        return pool.<String, JsonObject>get(habitus)
                .compose(item -> Ux.toFuture((T) item.getValue(dataKey)));
    }

    public <T> Future<T> set(final String dataKey, final T value) {
        return pool.<String, JsonObject>get(habitus)
                .compose(stored -> {
                    if (Ut.isNil(stored)) {
                        stored = new JsonObject();
                    }
                    /*
                     * Store dataKey = value
                     */
                    final JsonObject updated = stored.copy();
                    updated.put(dataKey, value);
                    return pool.put(habitus, updated)
                            .compose(nil -> Ux.toFuture(value));
                });
    }

    public Future<Boolean> clear() {
        /*
         * Remove reference pool for `habitus`
         */
        POOLS.remove(habitus);
        return pool.remove(habitus)
                /*
                 * Remove current habitus from pool ( Pool Structure )
                 */
                .compose(kv -> Future.succeededFuture(Boolean.TRUE));
    }
}
