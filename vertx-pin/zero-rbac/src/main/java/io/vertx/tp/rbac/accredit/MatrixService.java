package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.daos.RResourceMatrixDao;
import cn.vertxup.domain.tables.pojos.RResourceMatrix;
import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.tp.rbac.cv.em.OwnerType;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Ux;
import io.vertx.zero.matrix.DataBound;

import java.util.List;
import java.util.Objects;

public class MatrixService implements MatrixStub {

    @Override
    public Future<DataBound> fetchBound(final ScRequest request,
                                        final SResource resource) {
        /* User fetch first */
        final String userId = request.getUser();
        final String resourceId = resource.getKey();
        final String profileKey = Sc.generateProfileKey(resource);
        /* Fetch User First */
        return this.fetchMatrix(userId, resourceId)
                /* Whether userId exist */
                .compose(result -> Objects.isNull(result) ?
                        /*
                         * There is no matrix stored into database related to current user.
                         * Then find all role related matrices instead of current matrix.
                         * */
                        request.openSession()
                                /* Extract Roles from Privilege */
                                .compose(privilege -> privilege.asyncRole(profileKey))
                                .compose(roles -> this.fetchMatrix(roles, resourceId))
                        :
                        /*
                         * It means that there is defined user resource instead of role resource.
                         * In this situation, return to user's resource matrix directly.
                         */
                        MatrixFlow.toResult(result)
                )
                /* DataBound calculate */
                .compose(MatrixFlow::toBound);
    }

    @Override
    public Future<RResourceMatrix> fetchMatrix(final String userId, final String resourceId) {
        /* Find user matrix */
        final JsonObject filters = MatrixFlow.toFilters(resourceId);
        filters.put("owner", userId);
        filters.put("ownerType", OwnerType.USER.name());
        return Ux.Jooq.on(RResourceMatrixDao.class)
                .fetchOneAsync(new JsonObject().put("criteria", filters));
    }

    @Override
    public Future<List<RResourceMatrix>> fetchMatrix(final JsonArray roleIds, final String resourceId) {
        /* Find user matrix */
        final JsonObject filters = MatrixFlow.toFilters(resourceId);
        filters.put("owner,i", roleIds);
        filters.put("ownerType", OwnerType.ROLE.name());
        return Ux.Jooq.on(RResourceMatrixDao.class)
                .fetchAndAsync(new JsonObject().put("criteria", filters));
    }
}
