package io.vertx.up.extension;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.commune.Envelop;

/**
 * 「Extension」:
 * Name: Auditor System
 * Extension for auditing system in zero system
 * This sub system often happened before each Worker component.
 * It's configured in vertx-tp.yml file and will be triggered before
 * any worker method invoking.
 */
public interface PlugAuditor {
    /*
     * The object envelop should be modified in current method,
     * There is no default implementation in zero system.
     */
    void audit(RoutingContext context, Envelop envelop);
}
