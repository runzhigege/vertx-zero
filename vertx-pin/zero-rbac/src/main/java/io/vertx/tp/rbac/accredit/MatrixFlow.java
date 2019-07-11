package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.pojos.SView;
import io.vertx.core.Future;
import io.vertx.zero.atom.DataBound;

import java.util.ArrayList;
import java.util.List;

class MatrixFlow {

    static Future<List<SView>> toResult(final SView entity) {
        final List<SView> matrixList = new ArrayList<>();
        matrixList.add(entity);
        return Future.succeededFuture(matrixList);
    }

    static Future<DataBound> toBound(final List<SView> matrices) {
        final DataBound bound = new DataBound();
        matrices.forEach(matrix -> bound.addProjection(matrix.getProjection())
                .addRows(matrix.getRows())
                .addCriteria(matrix.getCriteria())
        );
        return Future.succeededFuture(bound);
    }
}
