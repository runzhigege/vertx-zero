package io.vertx.tp.jet.uca;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.jet.atom.JtUri;

/*
 * JtUri generate useful handler here
 */
public interface JtAim {
    /*
     * Main workflow here.
     */
    Handler<RoutingContext> attack(JtUri uri);
}
