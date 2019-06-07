package io.vertx.tp.rbac.authority;


import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Ux;
import io.vertx.up.exception._500InternalServerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Profile information to normalize all permission data
 * 1) After logged into the system , this class stored token information into POOL
 * 2) Get the json data and initialize profile information
 * {
 *     "user": "user id",
 *     "role": [
 *          {
 *              "roleId": "role id",
 *              "priority": "user role relation"
 *          },
 *          ...
 *     ],
 *     "group":[
 *          {
 *              "groupId": "group id",
 *              "priority": "user group relation",
 *              "role":[
 *                  {
 *                      "roleId": "role id",
 *                      "priority": "group role relation"
 *                  },
 *                  ...
 *              ]
 *          },
 *          ...
 *     ]
 * }
 * 3) Normalize the data to calculated permission pool
 *
 * The result should be:
 * 1) User Single Profile
 * 2) Group Multi Profile
 * 3) The final result should be role / permission
 *
 * Calculate different profile type
 */
public class ScSession {
    /*
     * Entry of authorization session.
     */
    public static Future<Boolean> initAuthorization(final JsonObject data) {
        /*
         * Initialization user's authorization pool
         */
        final String userKey = data.getString("user");
        return Sc.<JsonObject>cacheAuthority(userKey).compose(item -> {
            if (null == item) {
                /*
                 * Initialize role information
                 */
                return initRoles(data.getJsonArray("role"))
                        .compose(roles -> Future.succeededFuture(Boolean.TRUE));
            } else {
                /*
                 * Existing this profile
                 */
                return Future.succeededFuture(Boolean.TRUE);
            }
        });
    }

    /*
     * Initialization profile roles ( User )
     * 1) UNION
     * 2) EAGER
     * 3) LAZY
     * 4) INTERSECT
     */
    @SuppressWarnings("all")
    private static Future<JsonArray> initRoles(final JsonArray roles) {
        final List futures = new ArrayList<>();
        roles.stream().filter(Objects::nonNull).map(item -> (JsonObject) item)
                .map(ScProfile::new)
                .map(ScProfile::init)
                .forEach(futures::add);
        final Future<List<ScProfile>> result = Future.future();
        CompositeFuture.all(futures).setHandler(item -> {
            if (item.succeeded()) {
                final List<ScProfile> profile = item.result().list();
                result.complete(profile);
            } else {
                result.fail(new _500InternalServerException(ScSession.class, null));
            }
        });
        return result.compose(profiles -> Ux.toFuture(ScDetent.user().proc(profiles)));
    }
}
