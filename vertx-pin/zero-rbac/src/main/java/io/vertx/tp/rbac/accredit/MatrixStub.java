package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.pojos.RResourceMatrix;
import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.zero.matrix.DataBound;

import java.util.List;

/*
 * ResourceMatrix capture for user/role session storage
 */
public interface MatrixStub {
    /*
     * Fetch DataBound by:
     * request - userId, session, profile
     */
    Future<DataBound> fetchBound(ScRequest request, SResource resource);

    Future<RResourceMatrix> fetchMatrix(String user, String resourceId);

    Future<List<RResourceMatrix>> fetchMatrix(JsonArray role, String resourceId);
}
