package cn.vertxup.rbac.service.login;

import cn.vertxup.rbac.service.business.GroupStub;
import cn.vertxup.rbac.service.business.UserStub;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.up.unity.Uson;
import io.vertx.up.unity.Ux;

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
    public Future<JsonObject> execute(final String clientId, final String code, final Session session) {
        return codeStub.verify(clientId, code)
                /* Fetch role keys */
                .compose(item -> userStub.fetchRoleIds(item))

                /* Build Data in Token */
                .compose(roles -> Uson.create()
                        .append("user", clientId)

                        /* Session Pool will store user's critical permission data*/
                        .append("session", session.id())
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
            return userStub.fetchGroupIds(userKey)
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
                .forEach(item -> futures.add(groupStub.fetchRoleIdsAsync(item.getString(AuthKey.F_GROUP_ID))
                        .compose(roles -> Uson.create(item).append("role", roles).toFuture())
                ));
        return Ux.thenComposite(futures);
    }
}
