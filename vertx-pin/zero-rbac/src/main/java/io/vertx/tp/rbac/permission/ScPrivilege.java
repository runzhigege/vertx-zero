package io.vertx.tp.rbac.permission;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.util.Ut;

import java.io.Serializable;
import java.util.function.Function;

/*
 * Data in Session
 * 1) All the stored data is Buffer
 */
public class ScPrivilege implements Serializable {

    private final transient String habitusId;
    private transient ScHabitus habitus;

    private ScPrivilege(final String habitusId) {
        this.habitusId = habitusId;
    }

    public static Future<ScPrivilege> init(final JsonObject data) {
        final String habitus = data.getString(KeField.HABITUS);
        return new ScPrivilege(habitus).open();
    }

    public static Future<ScPrivilege> open(final String habitusId) {

        return null;
    }

    private Future<ScPrivilege> open() {

        return null;
    }

    // ----------------
    // Public interface that provide for operations
    // ----------------
    public Future<Boolean> evaluate(final Function<JsonObject, Future<Boolean>> fnDirect) {
        return fetchProfile().compose(profile -> Ut.isNil(profile) ?
                /*
                 * Profile does not exist and no cached, in this situation
                 * the system should go the whole flow
                 */
                fnDirect.apply(profile)
                /*
                 * Profile exist, it's not needed to go the whole flow.
                 * Return True directly.
                 */
                : Future.succeededFuture(Boolean.TRUE));
    }

    private Future<JsonObject> fetchProfile() {
        return Future.succeededFuture(new JsonObject());
    }

    public Future<JsonArray> fetchPermissions(final String profileKey) {

        return null;
    }

    public Future<JsonArray> fetchRoles(final String profileKey) {

        return null;
    }

    public Future<Boolean> fetchAuthorized(final String authorizeKey) {
        return null;
    }

    public Future<JsonObject> storeProfile(final JsonObject profiles) {

        return null;
    }

    public Future<JsonObject> storedBound(final String key, final JsonObject data) {

        return null;
    }

    public Future<Boolean> storedAuthorized(final String key, final Boolean result) {
        return null;
    }

}
