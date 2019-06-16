package io.vertx.up.rs.pointer;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.plugin.extension.PlugRegion;
import io.zero.epic.Ut;

class PluginRegion {
    /*
     * Plugin for Region
     */
    private static final String REGION = "region";

    static void before(final RoutingContext context, final Envelop envelop) {
        Plugin.mountPlugin(REGION, (auditCls, config) -> {
            /*
             * Data Region Before: Request
             */
            final PlugRegion region = Ut.singleton(auditCls);
            region.bind(config).before(context, envelop);
        });
    }

    static void after(final RoutingContext context, final Envelop envelop) {
        Plugin.mountPlugin(REGION, (auditCls, config) -> {
            /*
             * Data Region After: Response
             */
            final PlugRegion region = Ut.singleton(auditCls);
            region.bind(config).after(context, envelop);
        });
    }
}
