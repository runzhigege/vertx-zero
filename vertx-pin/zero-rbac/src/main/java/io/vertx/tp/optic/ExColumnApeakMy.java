package io.vertx.tp.optic;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.optic.fantom.Anchoret;
import io.vertx.tp.rbac.service.view.ViewService;
import io.vertx.tp.rbac.service.view.ViewStub;
import io.vertx.up.aiki.Ux;
import io.zero.epic.Ut;

import java.util.Objects;
import java.util.function.Function;

public class ExColumnApeakMy extends Anchoret<ApeakMy> implements ApeakMy {

    private final transient ViewStub stub = Ut.singleton(ViewService.class);

    @Override
    public Future<JsonArray> fetchMy(final JsonObject params) {
        final String userId = params.getString(ARG1);
        final String view = params.getString(ARG2);
        return this.uniform(params, (resourceId) -> this.stub.fetchMatrix(userId, resourceId, view)
                .compose(queried -> Objects.isNull(queried) ?
                        /* No view found */
                        Ux.toFuture(new JsonArray()) :
                        /* View found and get projection */
                        Ux.toFuture(Ut.toJArray(queried.getProjection()))
                )
        );
    }

    @Override
    public Future<JsonArray> saveMy(final JsonObject params, final JsonArray projection) {
        final String userId = params.getString(ARG1);
        final String view = params.getString(ARG2);
        return this.uniform(params, (resourceId) ->
                this.stub.saveMatrix(userId, resourceId, view, projection)
                        /* New projection */
                        .compose(updated -> Ux.toFuture(Ut.toJArray(updated.getProjection()))));
    }

    /*
     * consumer: resourceId
     */
    private Future<JsonArray> uniform(final JsonObject params, final Function<String, Future<JsonArray>> function) {
        final String resourceId = params.getString(ARG0);
        if (Ut.isNil(resourceId)) {
            return Ux.toFuture(new JsonArray());
        } else {
            return function.apply(resourceId);
        }
    }
}
