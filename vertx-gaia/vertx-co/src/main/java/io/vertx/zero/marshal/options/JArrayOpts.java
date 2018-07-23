package io.vertx.zero.marshal.options;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroVertx;

public class JArrayOpts implements Visitor<JsonArray> {

    private static final Node<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);

    @Override
    public JsonArray visit(final String... nodes) {
        Ut.ensureMinLength(this.getClass(), 0, (Object[]) nodes);
        final JsonObject tree = NODE.read();
        return Ut.visitJArray(tree, nodes);
    }
}
