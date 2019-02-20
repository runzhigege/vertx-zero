package io.vertx.up.web.anima;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;

/**
 * Data initialize: default with liquibase
 */
public class InitScatter implements Scatter<Vertx> {

    private static final Node<JsonObject> visitor = Ut.singleton(ZeroUniform.class);

    @Override
    public void connect(final Vertx vertx) {
        // inject configuration
        final JsonObject config = visitor.read();
        // initial node checking
        System.out.println(config.encodePrettily());
    }
}
