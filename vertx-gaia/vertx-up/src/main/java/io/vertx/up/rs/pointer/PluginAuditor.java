package io.vertx.up.rs.pointer;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.plugin.extension.PlugAuditor;
import io.vertx.up.epic.Ut;

class PluginAuditor {
    /*
     * Plugin for Auditor
     */
    private static final String AUDITOR = "auditor";

    static void audit(final RoutingContext context, final Envelop envelop) {
        Plugin.mountPlugin(AUDITOR, (auditCls, config) -> {
            /*
             * Extend PlugAuditor for auditing system setting for some spec business.
             */
            final PlugAuditor auditor = Ut.singleton(auditCls);
            auditor.audit(context, envelop);
        });
    }
}
