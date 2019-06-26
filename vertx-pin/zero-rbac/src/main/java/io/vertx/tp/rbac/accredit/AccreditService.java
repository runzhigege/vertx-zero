package io.vertx.tp.rbac.accredit;

import cn.vertxup.domain.tables.pojos.SAction;
import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.up.aiki.Ux;
import io.zero.epic.container.RxHod;

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
        /* RxHod for action / resource */
        final RxHod actionHod = new RxHod();
        final RxHod resourceHod = new RxHod();
        return this.authorizedWithCache(request, () -> this.actionStub.fetchAction(request.getNormalizedUri(), request.getMethod(), request.getSigma())

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

                /* The Final steps to execute matrix data here. */
                .compose(result -> this.authorized(result, request, resourceHod.get(), actionHod.get())))
                /*
                 * Refresh Credit Action
                 * This action must happen in each request, that's why the second time we also need to use ScRequest
                 * instead of SAction here.
                 * */
                .compose(result -> AccreditFlow.inspectImpact(result, request));
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
            return this.matrixStub.fetchBound(request, resource)
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
