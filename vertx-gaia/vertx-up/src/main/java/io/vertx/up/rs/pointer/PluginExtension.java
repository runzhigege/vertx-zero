package io.vertx.up.rs.pointer;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;

/*
 * Plugin Instance Pool for different usage.
 */
public interface PluginExtension {

    /*
     * The same path for class/method definition.
     * Normalizer.out
     */
    interface Answer {
        static void reply(final RoutingContext context, final Envelop envelop) {
            /* Response process */
            PluginRegion.after(context, envelop);
        }
    }

    /*
     * The same path for class/method definition.
     * Flower.continuous
     */
    interface Flower {
        static void continuous(final RoutingContext context, final Envelop envelop) {
            /* Auditor */
            PluginAuditor.audit(context, envelop);

            /* DataRegion before */
            PluginRegion.before(context, envelop);
        }
    }
}