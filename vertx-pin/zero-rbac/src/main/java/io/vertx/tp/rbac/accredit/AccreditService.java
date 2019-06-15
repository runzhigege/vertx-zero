package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScRequest;
import io.zero.epic.container.RxHod;

import javax.inject.Inject;

public class AccreditService implements AccreditStub {

    @Inject
    private transient ActionStub actionStub;

    @Inject
    private transient MatrixStub matrixStub;

    @Override
    public Future<Boolean> authorize(final JsonObject data) {
        final ScRequest request = new ScRequest(data);
        /* RxHod for action / resource */
        final RxHod actionHod = new RxHod();
        final RxHod resourceHod = new RxHod();
        return this.actionStub.fetchAction(request.getNormalizedUri(), request.getMethod(), request.getSigma())

                /* SAction checking for ( Uri + Method ) */
                .compose(action -> AccreditFlow.inspectAction(this.getClass(), action, request))
                .compose(actionHod::future)
                .compose(action -> this.actionStub.fetchResource(action.getResourceId()))

                /* SResource checking for ( ResourceId */
                .compose(resource -> AccreditFlow.inspectResource(this.getClass(), resource, request, actionHod.get()))

                /* Action Level Comparing */
                .compose(resource -> AccreditFlow.inspectLevel(this.getClass(), resource, actionHod.get()))
                .compose(resourceHod::future)

                /* Find Profile Permission and Check Profile */
                .compose(resource -> AccreditFlow.inspectPermission(this.getClass(), resource, request))

                /* Permission / Action Comparing */
                .compose(permissions -> AccreditFlow.inspectAuthorized(this.getClass(), actionHod.get(), permissions))

                /* The final steps to execute matrix data here. */
                .compose(result -> this.authorized(result, request, resourceHod.get()));
    }

    private Future<Boolean> authorized(final Boolean result, final ScRequest request, final SResource resource) {
        if (result) {
            return this.matrixStub.fetchBound(request, resource)
                    /* DataBound stored */
                    .compose(bound -> AccreditFlow.inspectBound(bound, request))
                    /* Authorized cached */
                    .compose(nil -> AccreditFlow.inspectAuthorized(request))
                    .compose(nil -> Future.succeededFuture(Boolean.TRUE));
        } else {
            return Future.succeededFuture(Boolean.FALSE);
        }
    }
}
