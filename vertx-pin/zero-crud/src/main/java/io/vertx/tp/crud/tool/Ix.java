package io.vertx.tp.crud.tool;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.atom.Envelop;

import java.util.function.Function;

public class Ix {

    public static <T> T inAdd(final String module, final Envelop envelop) {
        return IxPojo.inEntity(module, envelop, false);
    }

    public static <T> T inEdit(final String module, final Envelop envelop) {
        return IxPojo.inEntity(module, envelop, true);
    }

    public static JsonObject inKey(final String module, final Envelop envelop) {
        return IxQuery.inKey(module, envelop);
    }

    public static JsonObject inFilters(final String module, final Envelop envelop) {
        return IxPojo.inFilters(module, envelop);
    }

    public static <T> Future<JsonObject> toSingle(final String module, final Function<UxJooq, Future<T>> actuator) {
        return IxFn.toSingle(module, actuator);
    }

    public static Future<Boolean> toBoolean(final String module, final Function<UxJooq, Future<Boolean>> actuator) {
        return IxFn.toBoolean(module, actuator);
    }
}
