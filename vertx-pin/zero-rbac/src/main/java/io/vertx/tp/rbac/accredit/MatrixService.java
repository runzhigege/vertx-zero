package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.tp.rbac.service.view.ViewStub;
import io.vertx.zero.atom.DataBound;

import javax.inject.Inject;
import java.util.Objects;

public class MatrixService implements MatrixStub {

    @Inject
    private transient ViewStub stub;

    @Override
    public Future<DataBound> fetchBound(final ScRequest request,
                                        final SResource resource) {
        /* User fetch first */
        final String userId = request.getUser();
        final String resourceId = resource.getKey();
        final String profileKey = Sc.generateProfileKey(resource);
        /* Fetch User First */
        return this.stub.fetchMatrix(userId, resourceId, request.getView())
                /* Whether userId exist */
                .compose(result -> Objects.isNull(result) ?
                        /*
                         * There is no matrix stored into database related to current user.
                         * Then find all role related matrices instead of current matrix.
                         * */
                        request.openSession()
                                /* Extract Roles from Privilege */
                                .compose(privilege -> privilege.asyncRole(profileKey))
                                .compose(roles -> this.stub.fetchMatrix(roles, resourceId, request.getView()))
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
}
