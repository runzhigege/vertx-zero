package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtConstant;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.commune.Commercial;
import io.vertx.up.commune.config.Database;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.commune.config.DualMapping;
import io.vertx.up.commune.config.Identity;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxPool;
import io.vertx.up.util.Ut;

import java.util.Objects;

/*
 * Tool for different injection
 * 1）Database injection method
 * 2) Dict processing for `Dict` processing here.
 * 3) Diode processing for
 * - BEFORE -->
 * - AFTER  <--
 * - AROUND <->
 * 4) Enable plug-in for processing
 * - Dict plug-in for key = JsonArray processing
 * -- Dict ( Assist ) plugin here
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
                        Ux.future(commercial.database()) :
                        /*
                         * Cached database
                         */
                        Ux.future(cached))
                .compose(database -> pool.put(commercial.app(), database))
                .compose(kv -> Ux.future(kv.getValue()));
    }

    static Future<Boolean> componentAsync(final JtComponent component, final Commercial commercial) {
        if (Objects.nonNull(commercial)) {
            /*
             * JsonObject options inject ( without `mapping` node for Diode )
             */
            final JsonObject options = commercial.options();
            if (Ut.notNil(options)) {
                final JsonObject injectOpt = options.copy();
                injectOpt.remove(KeField.MAPPING);
                Ut.contract(component, JsonObject.class, injectOpt);
            }
            /*
             * Identity reference for id selector here
             */
            Ut.contract(component, Identity.class, commercial.identity());
            Ut.contract(component, DualMapping.class, commercial.mapping());
            /*
             * Recovery component here
             */
            Ut.contract(component, Dict.class, commercial.dict());
            /*
             * Because `DualMapping` has been configured here
             * And `Dict` has been configured in another way here
             * Old code:
             * Ut.contract(component, DualMapping.class, commercial.mapping());
             * Ut.contract(component, Dict.class, commercial.dict());
             */
            // Ut.contract(component, DualMapping.class, commercial.mapping());
            return Future.succeededFuture(Boolean.TRUE);
        } else {
            return Future.succeededFuture(Boolean.TRUE);
        }
    }
}
