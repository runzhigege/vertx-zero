package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.jet.cv.JtConstant;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxPool;
import io.vertx.up.commune.Api;
import io.vertx.zero.atom.Database;

import java.util.Objects;

/*
 * Tool for different injection
 */
class Anagogic {
    /*
     * Database processing
     */
    static Future<Database> databaseAsync(final Api api) {
        final UxPool pool = Ux.Pool.on(JtConstant.DEFAULT_POOL_DATABASE);
        return pool.<String, Database>get(api.app())
                /*
                 * Whether exist database in pool
                 */
                .compose(cached -> Objects.isNull(cached) ?
                        /*
                         * New database here
                         */
                        Ux.toFuture(api.database()) :
                        /*
                         * Cached database
                         */
                        Ux.toFuture(cached))
                .compose(database -> pool.put(api.app(), database))
                .compose(kv -> Ux.toFuture(kv.getValue()));
    }
}
