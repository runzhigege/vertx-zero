package io.vertx.tp.ambient.extension;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;
import io.vertx.up.fn.Fn;

/*
 * Prerequisite interface.
 * 1) Each app preparing progress should be defined by extension, not app module.
 * 2) When app has been initialized, here should be pre-condition processed first.
 * 3) This result should be JsonObject with:
 *    prerequisite = xxxx
 */
public interface Prerequisite {
    /*
     * Initializer generate method.
     */
    static Prerequisite generate(final Class<?> clazz) {
        return Fn.pool(Pool.PREREQUISITE_POOL, clazz.getName(), Ut.instance(clazz));
    }

    /*
     * This workflow happened before app initialization, it means that there is no
     * application key generated in this life-cycle.
     */
    Future<JsonObject> prepare(String appName);
}
