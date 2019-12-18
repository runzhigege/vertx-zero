package io.vertx.tp.optic;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.unity.UxJooq;

/*
 * View seeking interface
 * Fetch default view that will be impact and then the system will get
 * Resource Id here.
 */
public interface Seeker {

    String ARG0 = KeField.URI;
    String ARG1 = KeField.METHOD;
    String ARG2 = KeField.SIGMA;

    Seeker on(UxJooq jooq);

    /*
     * Seeker resource by params and return to unique resourceId
     * The target resource should be impact by input params
     */
    Future<JsonObject> fetchImpact(JsonObject params);
}
