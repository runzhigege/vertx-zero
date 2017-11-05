package io.vertx.zero.ke.node;

import com.vie.util.Ensurer;
import com.vie.util.Instance;
import com.vie.util.Jackson;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.Visitor;
import io.vertx.zero.ke.ZeroNode;
import io.vertx.zero.ke.config.ZeroVertx;

public class JArrayOpts implements Visitor<JsonArray> {

    private static final ZeroNode<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);

    @Override
    public JsonArray visit(final String... nodes) {
        Ensurer.gtLength(getClass(), 0, nodes);
        final JsonObject tree = NODE.read();
        return Jackson.visitJArray(tree, nodes);
    }
}
