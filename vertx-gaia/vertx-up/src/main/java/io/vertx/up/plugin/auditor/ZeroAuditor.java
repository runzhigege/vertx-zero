package io.vertx.up.plugin.auditor;

import io.vertx.up.atom.Envelop;

/*
 * Extension for auditing system in zero system
 * This sub system often happened before each Worker component.
 * It's configured in vertx-tp.yml file and will be triggered before
 * any worker method invoking.
 */
public interface ZeroAuditor {
    /*
     * The object envelop should be modified in current method,
     * There is no default implementation in zero system.
     */
    void audit(Envelop envelop);
}
