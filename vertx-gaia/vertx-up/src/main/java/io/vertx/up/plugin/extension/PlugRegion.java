package io.vertx.up.plugin.extension;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;

/**
 * 「Extension」
 * Name: Data Region
 * Data Region when you want to do some modification on Envelop,
 * There are two position to process region modification:
 * 1) Before Envelop request building & sending.
 * 2) After Response replying from agent.
 * This plugin exist in agent only and could not be used in worker, standard
 */
public interface PlugRegion {
    /*
     * Request processing
     */
    void before(RoutingContext context, Envelop request);

    /*
     * Response processing
     */
    void after(RoutingContext context, Envelop response);
}
