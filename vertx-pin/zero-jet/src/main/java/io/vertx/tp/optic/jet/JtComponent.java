package io.vertx.tp.optic.jet;

import io.vertx.core.Future;
import io.vertx.up.commune.ActRequest;
import io.vertx.up.commune.ActResponse;

/*
 * Business component, connect to dao, basic condition:
 */
public interface JtComponent {
    /*
     * Access for ActRequest here
     */
    Future<ActResponse> transferAsync(ActRequest request);
}
