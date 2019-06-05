package io.vertx.tp.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.tp.rbac.service.business.UserStub;
import io.vertx.up.aiki.Uson;

import javax.inject.Inject;

public class TokenService implements TokenStub {
    @Inject
    private transient CodeStub codeStub;
    @Inject
    private transient UserStub userStub;

    @Override
    public Future<JsonObject> execute(final String clientId, final String code, final String state) {
        return this.codeStub.verify(clientId, code)
                /* Fetch role keys */
                .compose(item -> this.userStub.fetchRoleIds(item))
                /* Build Data in Token */
                .compose(roles -> Uson.create()
                        .append("user", clientId)
                        .append("role", roles).toFuture()
                )
                /* Whether enable group feature */
                .compose(this::dispatchGroup);
    }

    private Future<JsonObject> dispatchGroup(final JsonObject response) {
        /*
         * Extract configuration of groupSupport
         */
        final ScConfig config = ScPin.getConfig();
        if (config.getSupportGroup()) {
            /*
             * Extract clientId
             */
            final String userKey = response.getString("user");
            return this.userStub.fetchGroupIds(userKey).compose(groups -> Uson.create(response)
                    .append("group", groups).toFuture());
        } else {
            return Future.succeededFuture(response);
        }
    }
}
