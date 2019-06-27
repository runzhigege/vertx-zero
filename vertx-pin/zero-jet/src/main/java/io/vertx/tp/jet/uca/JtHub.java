package io.vertx.tp.jet.uca;

import io.vertx.ext.web.Route;
import io.vertx.tp.jet.atom.JtUri;

/*
 * Route component building by Hub
 */
public interface JtHub {
    /*
     * Registry routing here, connect
     */
    void mount(Route route, JtUri uri);
}
