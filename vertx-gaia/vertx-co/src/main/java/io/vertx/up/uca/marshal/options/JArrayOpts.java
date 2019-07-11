package io.vertx.up.uca.marshal.options;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.uca.marshal.Visitor;
import io.vertx.up.uca.marshal.node.Node;
import io.vertx.up.uca.marshal.node.ZeroVertx;
import io.vertx.up.util.Ut;

public class JArrayOpts implements Visitor<JsonArray> {

    private static final Node<JsonObject> NODE
            = Ut.singleton(ZeroVertx.class);

    @Override
    public JsonArray visit(final String... nodes) {
        Ut.ensureMinLength(this.getClass(), 0, (Object[]) nodes);
        final JsonObject tree = NODE.read();
        return Ut.visitJArray(tree, nodes);
    }
}
