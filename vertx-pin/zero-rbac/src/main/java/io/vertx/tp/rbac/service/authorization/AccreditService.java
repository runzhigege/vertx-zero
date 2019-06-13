package io.vertx.tp.rbac.service.authorization;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.tp.rbac.cv.AuthKey;

import javax.inject.Inject;

public class AccreditService implements AccreditStub {

    @Inject
    private transient ResourceStub resourceStub;

    @Override
    public Future<Boolean> authorize(final JsonObject data) {
        final ScRequest request = new ScRequest(data.getJsonObject(AuthKey.F_METADATA));
        return this.resourceStub.fetchResource(request.getNormalizedUri(), request.getMethod())
                .compose(nil -> Future.succeededFuture());
    }
}
