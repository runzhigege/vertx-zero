package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.atom.JtApp;
import io.vertx.tp.jet.cv.JtConstant;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.component.Dictionary;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.commune.ActOut;
import io.vertx.up.commune.Commercial;
import io.vertx.up.commune.Envelop;
import io.vertx.up.commune.config.Database;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.commune.config.DualMapping;
import io.vertx.up.commune.config.Identity;
import io.vertx.up.fn.Fn;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxPool;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

    /*
     * Dict processing
     * 1) Check whether `dict` configured here
     * 2) If configured, the system should call `dict` plugin for
     */
    static Future<ConcurrentMap<String, JsonArray>> dictAsync(final Commercial commercial) {
        if (Objects.isNull(commercial)) {
            return Ux.future(new ConcurrentHashMap<>());
        } else {
            /*
             * Dict extract here
             */
            final Dict dict = commercial.dict();
            final ConcurrentMap<String, JsonArray> dictData = new ConcurrentHashMap<>();
            if (Objects.nonNull(dict) && dict.valid()) {
                /*
                 * Dict / dict
                 */
                final Class<?> dictCls = dict.getComponent();
                if (Ut.isImplement(dictCls, Dictionary.class)) {
                    /*
                     * JtDict instance for fetchAsync
                     */
                    final Dictionary dictStub = Fn.pool(Pool.POOL_DICT_SERVICE, dict.hashCode(),
                            () -> Ut.instance(dictCls));
                    /*
                     * Params here for different situations
                     */
                    final MultiMap paramMap = MultiMap.caseInsensitiveMultiMap();
                    paramMap.add(KeField.IDENTIFIER, commercial.identifier());
                    final JtApp app = Ambient.getApp(commercial.app());
                    if (Objects.nonNull(app)) {
                        paramMap.add(KeField.SIGMA, app.getSigma());
                        paramMap.add(KeField.APP_ID, app.getAppId());
                    }
                    /*
                     * Param Map / List<Source>
                     */
                    return dictStub.fetchAsync(paramMap, dict.getSource());
                } else return Ux.future(dictData);
            } else return Ux.future(dictData);
        }
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
             * Because `DualMapping` has been configured here
             * And `Dict` has been configured in another way here
             * Old code:
             * Ut.contract(component, DualMapping.class, commercial.mapping());
             * Ut.contract(component, Dict.class, commercial.dict());
             */
            // Ut.contract(component, DualMapping.class, commercial.mapping());
            // Ut.contract(component, Dict.class, commercial.dict());
            return Future.succeededFuture(Boolean.TRUE);
        } else {
            return Future.succeededFuture(Boolean.TRUE);
        }
    }

    /*
     * Final complete in channel
     */
    static Future<Envelop> complete(final ActOut output, final DualMapping mapping, final Envelop request) {
        /*
         * Data Extract from output
         */
        final Envelop response = output.envelop(mapping);
        response.setMethod(request.getMethod());
        response.setUri(request.getUri());
        response.setUser(request.user());
        response.setSession(request.getSession());
        response.setHeaders(request.headers());
        return null;
    }
}
