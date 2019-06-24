package io.vertx.tp.rbac.extension;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.extension.jooq.AbstractJooq;
import io.vertx.tp.ke.extension.jooq.Seeker;
import io.vertx.tp.rbac.accredit.ActionService;
import io.vertx.tp.rbac.accredit.ActionStub;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Uson;
import io.zero.epic.Ut;

/*
 * Seek impact resource for params here, it will be passed by crud
 */
public class ResourceSeeker extends AbstractJooq<Seeker> implements Seeker {

    private transient final ActionStub stub = Ut.singleton(ActionService.class);

    @Override
    public Future<JsonObject> fetchImpact(final JsonObject params) {

        final JsonObject seekers = params.getJsonObject(PARAM_SEEKER);
        /*
         * Uri, Method
         */
        final String uri = seekers.getString(KeField.URI);
        final HttpMethod method = HttpMethod.valueOf(seekers.getString(KeField.METHOD));
        final String sigma = params.getString(KeField.SIGMA);
        Sc.infoResource(this.getLogger(), AuthMsg.SEEKER_RESOURCE, uri, method, sigma);
        return this.stub.fetchAction(uri, method, sigma)
                .compose(action -> Uson.create(params)
                        .remove(PARAM_SEEKER)
                        .append(KeField.RESOURCE_ID, action.getResourceId())
                        .toFuture());
    }
}
