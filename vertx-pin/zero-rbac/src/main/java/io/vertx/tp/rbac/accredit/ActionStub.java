package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.pojos.SAction;
import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;

public interface ActionStub {

    Future<SAction> fetchAction(String normalizedUri, HttpMethod method);

    Future<SAction> fetchAction(String normalizedUri, HttpMethod method, String sigma);

    Future<SResource> fetchResource(String key);
}
