package io.vertx.tp.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.service.business.UserStub;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;

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
                .compose(item -> this.userStub.fetchRoles(clientId))
                .compose(roleIds -> Ux.toFuture(roleIds.getList()))
                /* Build Data in Token */
                .compose(roles -> Uson.create()
                        .append("user", clientId)
                        .append("role", roles)
                        .toFuture()
                );
    }
}
