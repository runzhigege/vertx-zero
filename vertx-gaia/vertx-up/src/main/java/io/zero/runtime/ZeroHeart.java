package io.zero.runtime;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;

public class ZeroHeart {

    private static final String INIT = "init";
    private static final Node<JsonObject> visitor = Ut.singleton(ZeroUniform.class);

    public static void init() {
        // inject configuration
        final JsonObject config = visitor.read();
        /*
         * Check whether there exist `init` node for class
         * Each `init` clazz must be configured as
         * init:
         * - clazz: XXXX
         *   config:
         *      key1:value1
         *      key2:value2
         */
        if (config.containsKey(INIT)) {
            final JsonArray initArray = config.getJsonArray(INIT);
            /* Component Init */
            Ut.itJArray(initArray, (init, index) -> Ux.initComponent(init));
        }
    }
}
