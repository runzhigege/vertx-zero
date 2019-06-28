package io.vertx.tp.jet.uca.aim;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.jet.uca.JtAim;

public class EngineAim implements JtAim {
    @Override
    public Handler<RoutingContext> attack(final JtUri uri) {

        return context -> {

        };
    }
}
