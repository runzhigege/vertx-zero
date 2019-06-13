package io.vertx.tp.rbac.service.authorization;

import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;

public interface ResourceStub {

    Future<SResource> fetchResource(final String normalizedUri, final HttpMethod method);
}
