package io.vertx.zero.ke.node;

import com.vie.hors.ZeroException;
import com.vie.util.Ensurer;
import com.vie.util.Instance;
import com.vie.util.Jackson;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.Visitor;
import io.vertx.zero.ke.ZeroNode;
import io.vertx.zero.ke.config.ZeroVertx;

public class JsonOpts implements Visitor<JsonObject> {

    private static final ZeroNode<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);

    @Override
    public JsonObject visit(final String... nodes)
            throws ZeroException {
        Ensurer.gtLength(getClass(), 0, nodes);
        // Tree Data
        final JsonObject tree = NODE.read();
        return Jackson.visitJObject(tree, nodes);
    }
}
