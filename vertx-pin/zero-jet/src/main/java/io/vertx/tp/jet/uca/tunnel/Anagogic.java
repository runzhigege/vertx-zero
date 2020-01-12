package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtConstant;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.commune.Commercial;
import io.vertx.up.commune.Envelop;
import io.vertx.up.commune.config.*;
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

    static Future<Boolean> componentAsync(final JtComponent component, final Envelop envelop) {
        final JsonObject headers = envelop.headersX();
        final XHeader header = new XHeader();
        header.fromJson(headers);
        Ut.contract(component, XHeader.class, header);
        return Ux.future(Boolean.TRUE);
    }

    static Future<Boolean> componentAsync(final JtComponent component, final Commercial commercial) {
        if (Objects.nonNull(commercial)) {
            /*
             * JsonObject options inject ( without `mapping` node for Diode )
             */
            final JsonObject options = commercial.options();
            if (Ut.notNil(options)) {
                final JsonObject injectOpt = options.copy();
                Ut.contract(component, JsonObject.class, injectOpt);
            }
            /*
             * Identity reference for id selector here
             */
            Ut.contract(component, Identity.class, commercial.identity());
            Ut.contract(component, DualMapping.class, commercial.mapping());
            Ut.contract(component, Dict.class, commercial.dict());
            return Future.succeededFuture(Boolean.TRUE);
        } else {
            return Future.succeededFuture(Boolean.TRUE);
        }
    }
}
