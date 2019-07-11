package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.jet.cv.JtConstant;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxPool;
import io.vertx.up.atom.Database;
import io.vertx.up.commune.Commercial;

import java.util.Objects;

/*
 * Tool for different injection
 */
class Anagogic {
    /*
     * Database processing
     */
    static Future<Database> databaseAsync(final Commercial commercial) {
        final UxPool pool = Ux.Pool.on(JtConstant.DEFAULT_POOL_DATABASE);
        return pool.<String, Database>get(commercial.app())
                /*
                 * Whether exist database in pool
                 */
                .compose(cached -> Objects.isNull(cached) ?
                        /*
                         * New database here
                         */
                        Ux.toFuture(commercial.database()) :
                        /*
                         * Cached database
                         */
                        Ux.toFuture(cached))
                .compose(database -> pool.put(commercial.app(), database))
                .compose(kv -> Ux.toFuture(kv.getValue()));
    }
}
