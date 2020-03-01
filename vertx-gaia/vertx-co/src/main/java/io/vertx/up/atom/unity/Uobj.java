package io.vertx.up.atom.unity;

import io.vertx.core.json.JsonArray;
import io.vertx.up.util.Ut;

/**
 * @author lang
 * Stream tool for <T> type of entity
 */
public class Uobj {

    private final transient JsonArray data = new JsonArray();

    private Uobj(final JsonArray data) {
        if (Ut.notNil(data)) {
            this.data.addAll(data);
        }
    }

    public static Uobj create(final JsonArray data) {
        return new Uobj(data);
    }
}
