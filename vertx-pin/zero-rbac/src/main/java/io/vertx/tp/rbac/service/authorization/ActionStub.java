package io.vertx.tp.rbac.service.authorization;

import cn.vertxup.domain.tables.pojos.SAction;
import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;

public interface ActionStub {

    Future<SAction> fetchAction(final String normalizedUri, final HttpMethod method);

    Future<SResource> fetchResource(final String key);
}
