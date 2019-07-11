package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.zero.atom.DataBound;

/*
 * ResourceMatrix capture for user/role session storage
 */
public interface MatrixStub {
    /*
     * Fetch DataBound by:
     * request - userId, session, profile
     */
    Future<DataBound> fetchBound(ScRequest request, SResource resource);
}
