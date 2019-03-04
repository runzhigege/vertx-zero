package io.god.engine;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.up.rs.PlugRouter;

public class RouterSplitter implements PlugRouter {

    @Override
    public void mount(final Router router, final JsonObject config) {
        router.route("/test").handler(context -> {
            System.out.println("Akka");
        });
    }
}
