package io.vertx.tp.rbac.service.authorization;

import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;

public class ResourceService implements ResourceStub {

    @Override
    public Future<SResource> fetchResource(final String normalizedUri,
                                           final HttpMethod method) {
        System.out.println(normalizedUri);
        System.out.println(method);
        return Future.succeededFuture(null);
    }
}
