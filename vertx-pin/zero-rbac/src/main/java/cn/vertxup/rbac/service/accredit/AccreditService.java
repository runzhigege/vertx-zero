package cn.vertxup.rbac.service.accredit;

import cn.vertxup.rbac.domain.tables.pojos.SAction;
import cn.vertxup.rbac.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.up.aiki.Ux;
import io.vertx.up.epic.container.Refer;

import javax.inject.Inject;
import java.util.function.Supplier;

public class AccreditService implements AccreditStub {

    @Inject
    private transient ActionStub actionStub;

    @Inject
    private transient MatrixStub matrixStub;

    @Override
    public Future<Boolean> authorize(final JsonObject data) {
        final ScRequest request = new ScRequest(data);
        /* Refer for action / resource */
        final Refer actionHod = new Refer();
        final Refer resourceHod = new Refer();
        return authorizedWithCache(request, () -> actionStub.fetchAction(request.getNormalizedUri(), request.getMethod(), request.getSigma())

                /* SAction checking for ( Uri + Method ) */
                .compose(action -> AccreditFlow.inspectAction(getClass(), action, request))
                .compose(actionHod::future)
                .compose(action -> actionStub.fetchResource(action.getResourceId()))

                /* SResource checking for ( ResourceId */
                .compose(resource -> AccreditFlow.inspectResource(getClass(), resource, request, actionHod.get()))

                /* Action Level Comparing */
                .compose(resource -> AccreditFlow.inspectLevel(getClass(), resource, actionHod.get()))
                .compose(resourceHod::future)

                /* Find Profile Permission and Check Profile */
                .compose(resource -> AccreditFlow.inspectPermission(getClass(), resource, request))

                /* Permission / Action Comparing */
                .compose(permissions -> AccreditFlow.inspectAuthorized(getClass(), actionHod.get(), permissions))

                /* The Final steps to execute matrix data here. */
                .compose(result -> authorized(result, request, resourceHod.get(), actionHod.get())));
    }

    private Future<Boolean> authorizedWithCache(final ScRequest request, final Supplier<Future<Boolean>> supplier) {
        final String authorizedKey = request.getAuthorizedKey();
        return request.openSession()
                /* Get data from cache */
                .compose(privilege -> privilege.asyncAuthorized(authorizedKey))
                /* */
                .compose(result -> result ? Ux.toFuture(Boolean.TRUE) :
                        supplier.get());
    }

    private Future<Boolean> authorized(final Boolean result, final ScRequest request,
                                       final SResource resource, final SAction action) {
        if (result) {
            return matrixStub.fetchBound(request, resource)
                    /* DataBound credit parsing from SAction */
                    .compose(bound -> Ux.toFuture(bound.addCredit(action.getRenewalCredit())))
                    /* DataBound stored */
                    .compose(bound -> AccreditFlow.inspectBound(bound, request))
                    /* Authorized cached and get result */
                    .compose(nil -> AccreditFlow.inspectAuthorized(request));
        } else {
            return Future.succeededFuture(Boolean.FALSE);
        }
    }
}
