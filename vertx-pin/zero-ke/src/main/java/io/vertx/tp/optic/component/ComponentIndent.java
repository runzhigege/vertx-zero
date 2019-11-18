package io.vertx.tp.optic.component;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * Identifier selector for some specific situation
 * 1) For channel, it's bind to `identifier` that's static in `I_SERVICE`
 * 2) Another `identifier` for switching here is that using `Identity` to
 *    be selected here. It enhance the usage of `identifier`.
 *
 * The concept in Zero Extension should be
 * 1) sigma, the application identifier that could be bind
 * -- 1.1) Each application contains only one sigma
 * -- 1.2) Multi applications belong to one sigma
 * 2) identifier, The model id in our framework to identify model
 * -- 2.1) Model unique id should be `namespace + identifier` here.
 */
public interface ComponentIndent {
    /*
     * Get identifier directly by params
     */
    Future<String> resolve(JsonObject data);
}