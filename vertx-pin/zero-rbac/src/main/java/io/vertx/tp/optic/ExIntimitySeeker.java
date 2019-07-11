package io.vertx.tp.optic;

import cn.vertxup.rbac.service.accredit.ActionService;
import cn.vertxup.rbac.service.accredit.ActionStub;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.fantom.Anchoret;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Uson;
import io.vertx.up.epic.Ut;

/*
 * Seek impact resource for params here, it will be passed by crud
 */
public class ExIntimitySeeker extends Anchoret<Seeker> implements Seeker {

    private transient final ActionStub stub = Ut.singleton(ActionService.class);

    @Override
    public Future<JsonObject> fetchImpact(final JsonObject params) {
        /*
         * Uri, Method
         */
        final String uri = params.getString(ARG0);
        final HttpMethod method = HttpMethod.valueOf(params.getString(ARG1));
        final String sigma = params.getString(ARG2);
        Sc.infoResource(getLogger(), AuthMsg.SEEKER_RESOURCE, uri, method, sigma);
        return stub.fetchAction(uri, method, sigma)
                .compose(action -> Uson.create(params)
                        .append(KeField.RESOURCE_ID, action.getResourceId())
                        .toFuture());
    }
}
