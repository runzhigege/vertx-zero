package io.vertx.up.uca.marshal.options;

import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.ZeroException;
import io.vertx.up.uca.marshal.Visitor;
import io.vertx.up.uca.marshal.node.Node;
import io.vertx.up.uca.marshal.node.ZeroVertx;
import io.vertx.up.util.Ut;

public class JObjectOpts implements Visitor<JsonObject> {

    private static final Node<JsonObject> NODE
            = Ut.singleton(ZeroVertx.class);

    @Override
    public JsonObject visit(final String... nodes)
            throws ZeroException {
        Ut.ensureMinLength(this.getClass(), 0, (Object[]) nodes);
        // Tree Data
        final JsonObject tree = NODE.read();
        return Ut.visitJObject(tree, nodes);
    }
}
