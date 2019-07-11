package io.vertx.tp.ambient.extension;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.epic.Ut;
import io.vertx.zero.fn.Fn;

import java.util.function.Function;

/*
 * OOB Data initialization for The whole application.
 * 1) XApp: Application Data
 * 2) XSource: Data Source Data
 * 3) Extension: Configuration For Initializer Extension for other flow.
 */
public interface Init {
    static Init app() {
        return Fn.pool(Pool.INIT_POOL, AppInit.class.getName(), AppInit::new);
    }

    static Init source() {
        return Fn.pool(Pool.INIT_POOL, SourceInit.class.getName(), SourceInit::new);
    }

    static Init database() {
        return Fn.pool(Pool.INIT_POOL, DatabaseInit.class.getName(), DatabaseInit::new);
    }

    static Init data() {
        return Fn.pool(Pool.INIT_POOL, DatumInit.class.getName(), DatumInit::new);
    }

    /*
     * Initializer generate method.
     */
    static Init generate(final Class<?> clazz) {
        return Fn.pool(Pool.INIT_POOL, clazz.getName(), () -> Ut.instance(clazz));
    }

    /*
     * Executor Constructor
     */
    Function<JsonObject, Future<JsonObject>> apply();

    /*
     * Unique condition for current object
     */
    default JsonObject whereUnique(final JsonObject input) {
        /* Default situation, nothing to do */
        return new JsonObject();
    }

    /*
     * Executor result hooker
     */
    default JsonObject result(final JsonObject input, final JsonObject appJson) {
        /* Default situation, return to appJson */
        return appJson;
    }
}
