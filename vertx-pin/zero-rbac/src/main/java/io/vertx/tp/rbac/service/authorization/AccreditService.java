package io.vertx.tp.rbac.service.authorization;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class AccreditService implements AccreditStub {

    @Override
    public Future<Boolean> authorize(final JsonObject data) {
        System.err.println(data);
        return Future.succeededFuture(Boolean.TRUE);
    }
}
