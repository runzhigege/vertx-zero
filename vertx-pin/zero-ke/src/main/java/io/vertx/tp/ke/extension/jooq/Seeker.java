package io.vertx.tp.ke.extension.jooq;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.UxJooq;

/*
 * View seeking interface
 * Fetch default view that will be impact and then the system will get
 * Resource Id here.
 */
public interface Seeker {

    String PARAM_SEEKER = "seeker";

    Seeker on(UxJooq jooq);

    /*
     * Seeker resource by params and return to unique resourceId
     * The target resource should be impact by input params
     */
    Future<JsonObject> fetchImpact(JsonObject params);
}
