package io.vertx.tp.optic.jet;

import io.vertx.core.Future;
import io.vertx.up.commune.ActRequest;
import io.vertx.up.commune.ActResponse;
import io.vertx.up.commune.ZApi;

/*
 * Business component, connect to dao, basic condition:
 */
public interface JtComponent {

    Future<ActResponse> transferAsync(ActRequest request);

    JtComponent bind(ZApi api);
}
