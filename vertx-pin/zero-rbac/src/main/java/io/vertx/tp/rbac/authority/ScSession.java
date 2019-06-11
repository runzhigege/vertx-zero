package io.vertx.tp.rbac.authority;


import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;

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
    private static final Annal LOGGER = Annal.get(ScSession.class);

    /*
     * Entry of authorization session.
     */
    public static Future<Boolean> initAuthorization(final JsonObject data) {
        /*
         * Initialization user's authorization pool
         */
        final String userKey = data.getString("user");
        /* Authorities Json Data */
        final JsonObject authority = new JsonObject();
        return Sc.<JsonObject>cacheAuthority(userKey).compose(item -> {
            if (null == item) {
                /* Initialize roles information */
                return initRoles(authority, data.getJsonArray("role"))
                        /* Initialize group information */
                        .compose(processed -> initGroups(processed, data.getJsonArray("group")))
                        /* Refresh Cache */
                        .compose(result -> Uson.create(data)
                                .append("profile", authority)
                                .toFuture())
                        .compose(session -> Sc.cacheAuthority(userKey, session))
                        /* Report information */
                        .compose(ScSession::onReport)
                        /* Result */
                        .compose(nil -> Ux.toFuture(Boolean.TRUE));
            } else {
                /*
                 * Existing this profile, fill data iinto authority
                 */
                authority.mergeIn(item);
                return Ux.toFuture(Boolean.TRUE);
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
    private static Future<JsonObject> initRoles(final JsonObject authority, final JsonArray roles) {
        Sc.infoAuth(LOGGER, "Roles : {0}", roles.encode());
        final List futures = new ArrayList<>();
        roles.stream().filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .map(ProfileRole::new)
                .map(ProfileRole::initAsync)
                .forEach(futures::add);
        return CompositeFuture.all(futures)
                /* Composite Result */
                .compose(Sc::<ProfileRole>composite)
                /* User Process */
                .compose(ScDetent.user(authority)::procAsync);
    }

    @SuppressWarnings("all")
    private static Future<JsonObject> initGroups(final JsonObject authority, final JsonArray groups) {
        Sc.infoAuth(LOGGER, "Groups: {0}", groups.encode());
        final List futures = new ArrayList();
        groups.stream().filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .map(ProfileGroup::new)
                .map(ProfileGroup::initAsync)
                .forEach(futures::add);
        return CompositeFuture.all(futures).compose(Sc::<ProfileGroup>composite).compose(profiles -> Ux.toFuture(profiles)
                /* Group Direct Mode */
                .compose(Align::flat)
                .compose(ScDetent.group(authority)::procAsync)
                .compose(nil -> Ux.toFuture(profiles))

                /* Group Parent Mode */
                .compose(Align::parent)
                /** Parent Only */
                .compose(parents -> ScDetent.parent(authority, profiles).procAsync(parents)
                        /** Parent and Current */
                        .compose(nil -> ScDetent.inherit(authority, profiles).procAsync(parents)))
                .compose(nil -> Ux.toFuture(profiles))

                /* Group Child Mode */
                .compose(Align::children)
                /** Child Only */
                .compose(children -> ScDetent.children(authority, profiles).procAsync(children)
                        /** Child and Current */
                        .compose(nil -> ScDetent.extend(authority, profiles).procAsync(children)))
                .compose(nil -> Ux.toFuture(profiles))
        ).compose(nil -> Ux.toFuture(authority));
    }

    private static Future<JsonObject> onReport(final JsonObject result) {
        Sc.infoAuth(LOGGER, "Permissions: \n{0}", result.encodePrettily());
        return Future.succeededFuture(result);
    }
}
