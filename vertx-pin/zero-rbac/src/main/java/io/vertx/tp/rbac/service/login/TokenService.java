package io.vertx.tp.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.service.business.UserStub;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;

import javax.inject.Inject;
import java.util.Objects;
import java.util.stream.Collectors;

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
                .compose(roles -> Ux.toFuture(roles.stream()
                        .filter(Objects::nonNull)
                        .map(item -> (JsonObject) item)
                        .map(relation -> relation.getString(AuthKey.F_ROLE_ID))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
                ))
                /* Build Data in Token */
                .compose(roles -> Uson.create()
                        .append("user", clientId)
                        .append("role", roles)
                        .toFuture()
                );
    }
}
