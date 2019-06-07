package io.vertx.tp.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.tp.rbac.service.business.GroupStub;
import io.vertx.tp.rbac.service.business.UserStub;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TokenService implements TokenStub {
    @Inject
    private transient CodeStub codeStub;
    @Inject
    private transient UserStub userStub;
    @Inject
    private transient GroupStub groupStub;

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
                .compose(this::fetchGroup);
    }

    private Future<JsonObject> fetchGroup(final JsonObject response) {
        /*
         * Extract configuration of groupSupport
         */
        final ScConfig config = ScPin.getConfig();
        if (config.getSupportGroup()) {

            /*
             * Extract clientId
             */
            final String userKey = response.getString("user");
            return this.userStub.fetchGroupIds(userKey)
                    .compose(this::fetchRoles)
                    .compose(groups -> Uson.create(response)
                            .append("group", groups).toFuture());
        } else {
            return Future.succeededFuture(response);
        }
    }

    private Future<JsonArray> fetchRoles(final JsonArray groups) {
        /* Future List */
        final List<Future<JsonObject>> futures = new ArrayList<>();
        groups.stream().filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .forEach(item -> futures.add(this.groupStub.fetchRoleIds(item.getString(AuthKey.F_GROUP_ID))
                        .compose(roles -> Uson.create(item).append("role", roles).toFuture())
                ));
        return Ux.thenComposite(futures);
    }
}
