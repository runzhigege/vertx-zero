package io.vertx.tp.rbac.extension;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.up.atom.Envelop;
import io.vertx.up.plugin.extension.PlugRegion;

/*
 * Extension in RBAC module
 */
public class DataRegion implements PlugRegion {

    @Override
    public void before(final RoutingContext context, final Envelop request) {
        final Session session = context.session();

    }

    @Override
    public void after(final RoutingContext context, final Envelop response) {

    }
}
