package io.vertx.tp.ke.extension.jooq;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.UxJooq;

/*
 * View seeking interface
 * Fetch default view that will be impact and then the system will get
 * Resource Id here.
 */
public interface Manhunt {

    Manhunt on(UxJooq jooq);

    /*
     * Seek resource by params and return to unique resourceId
     */
    Future<String> seekResource(JsonObject params);
}
