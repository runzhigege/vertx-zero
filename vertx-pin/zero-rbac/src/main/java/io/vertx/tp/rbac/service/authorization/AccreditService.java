package io.vertx.tp.rbac.service.authorization;

import cn.vertxup.domain.tables.pojos.SAction;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._403ActionMissingException;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.up.exception.WebException;

import javax.inject.Inject;
import java.util.Objects;

public class AccreditService implements AccreditStub {

    @Inject
    private transient ActionStub actionStub;

    @Override
    public Future<Boolean> authorize(final JsonObject data) {
        final ScRequest request = new ScRequest(data.getJsonObject(AuthKey.F_METADATA));
        return this.actionStub.fetchAction(request.getNormalizedUri(), request.getMethod())
                /* SAction checking for ( Uri + Method ) */
                .compose(action -> this.actionDinned(action, request))
                .compose(action -> this.actionStub.fetchResource(action.getResourceId())
                        /* Get resource by action */
                        .compose(resource -> {

                            return Future.succeededFuture();
                        })
                );
    }

    private Future<SAction> actionDinned(final SAction action, final ScRequest request) {
        if (Objects.isNull(action)) {
            final String requestUri = request.getMethod() + " " + request.getNormalizedUri();
            final WebException error = new _403ActionMissingException(this.getClass(), requestUri);
            return Future.failedFuture(error);
        } else {
            return Future.succeededFuture(action);
        }
    }
}
