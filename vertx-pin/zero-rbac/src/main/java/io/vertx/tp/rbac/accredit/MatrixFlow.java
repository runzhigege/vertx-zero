package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.pojos.RResourceMatrix;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.matrix.DataBound;

import java.util.ArrayList;
import java.util.List;

class MatrixFlow {

    static Future<List<RResourceMatrix>> toResult(final RResourceMatrix entity) {
        final List<RResourceMatrix> matrixList = new ArrayList<>();
        matrixList.add(entity);
        return Future.succeededFuture(matrixList);
    }

    static Future<DataBound> toBound(final List<RResourceMatrix> matrices) {
        final DataBound bound = new DataBound();
        matrices.forEach(matrix -> bound.addProjection(matrix.getProjection())
                .addRows(matrix.getRows())
                .addCriteria(matrix.getCriteria())
        );
        return Future.succeededFuture(bound);
    }

    static JsonObject toFilters(final String resourceId) {
        final JsonObject filters = new JsonObject();
        filters.put(Strings.EMPTY, Boolean.TRUE);
        filters.put("resourceId", resourceId);
        return filters;
    }
}
