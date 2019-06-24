package io.vertx.tp.rbac.extension;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.extension.jooq.AbstractJooq;
import io.vertx.tp.ke.extension.jooq.ApeakMy;
import io.vertx.tp.rbac.service.view.ViewService;
import io.vertx.tp.rbac.service.view.ViewStub;
import io.vertx.up.aiki.Ux;
import io.zero.epic.Ut;

import java.util.Objects;
import java.util.function.BiFunction;

public class ColumnMyService extends AbstractJooq<ApeakMy> implements ApeakMy {

    private final transient ViewStub stub = Ut.singleton(ViewService.class);

    @Override
    public Future<JsonArray> fetchMy(final JsonObject params) {
        return this.uniform(params, (resourceId, userId) -> this.stub.fetchMatrix(userId, resourceId, params.getString("view"))
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
        return this.uniform(params, (resourceId, userId) ->
                this.stub.saveMatrix(userId, resourceId, params.getString("view"), params.getString(KeField.SIGMA), projection)
                        .compose(updated -> Ux.toFuture(Ut.toJArray(updated.getProjection()))));
    }

    /*
     * consumer: resourceId, userId
     */
    private Future<JsonArray> uniform(final JsonObject params, final BiFunction<String, String, Future<JsonArray>> function) {
        final String resourceId = params.getString(KeField.RESOURCE_ID);
        if (Ut.isNil(resourceId)) {
            return Ux.toFuture(new JsonArray());
        } else {
            /* User */
            final JsonObject principle = params.getJsonObject(KeField.PRINCIPLE);
            final JsonObject token = Ux.Jwt.extract(principle.getString("jwt"));
            final String userId = token.getString("user");
            return function.apply(resourceId, userId);
        }
    }
}
