package io.vertx.tp.rbac.atom;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.up.aiki.Ux;

import java.io.Serializable;
import java.util.Objects;

/*
 * Data in Session
 * 1) All the stored data is Buffer
 */
public class ScPrivilege implements Serializable {

    private final transient String sessionId;
    private transient Session session;

    private ScPrivilege(final String sessionId) {
        this.sessionId = sessionId;
    }

    public static Future<ScPrivilege> init(final JsonObject data) {
        final String sessionId = data.getString("session");
        return open(sessionId).compose(self -> self.storeData(data));
    }

    static Future<ScPrivilege> open(final String sessionId) {
        return new ScPrivilege(sessionId).open();
    }

    // ---- Instance method
    public JsonObject getProfile() {
        final Buffer profile = this.session.get("profile");
        if (Objects.nonNull(profile)) {
            return profile.toJsonObject();
        } else {
            return new JsonObject();
        }
    }

    // Async private workflow
    private Future<JsonObject> asyncProfile() {
        if (Objects.isNull(this.session)) {
            return this.open().compose(nil -> Ux.toFuture(this.getProfile()));
        } else {
            return Ux.toFuture(this.getProfile());
        }
    }

    /*
     * Async analyze permission
     */
    public Future<JsonArray> asyncPermission(final String profileKey) {
        return this.asyncProfile()
                .compose(profile -> Ux.toFuture(profile.getJsonObject(profileKey)))
                .compose(single -> Ux.toFuture(single.getJsonArray(AuthKey.PROFILE_PERM)));
    }

    /*
     * Async analyze role
     */
    public Future<JsonArray> asyncRole(final String profileKey) {
        return this.asyncProfile()
                .compose(profile -> Ux.toFuture(profile.getJsonObject(profileKey)))
                .compose(single -> Ux.toFuture(single.getJsonArray(AuthKey.PROFILE_ROLE)));
    }

    /*
     * Async result
     */
    public Future<Boolean> asyncAuthorized(final String key) {
        Boolean result = this.session.get(key);
        if (Objects.isNull(result)) {
            result = Boolean.FALSE;
        }
        return Ux.toFuture(result);
    }

    public Future<JsonObject> storedBound(final String key, final JsonObject data) {
        if (Objects.nonNull(this.session)) {
            this.session.put(key, data.toBuffer());
        }
        return Ux.toFuture(data);
    }

    public Future<Boolean> storedFinal(final String key, final Boolean result) {
        if (Objects.nonNull(this.session)) {
            this.session.put(key, result);
        }
        return Ux.toFuture(result);
    }

    // --- Private method below
    private Future<ScPrivilege> open() {
        return Ke.session(this.sessionId).compose(session -> {
            this.session = session;
            return Ux.toFuture(this);
        });
    }

    private Future<ScPrivilege> storeData(final JsonObject data) {
        final String user = data.getString("user");
        final JsonArray role = data.getJsonArray("role");
        final JsonArray group = data.getJsonArray("group");
        this.session.put("user", user);
        this.session.put("role", role.toBuffer());
        if (Objects.nonNull(group)) {
            this.session.put("group", group.toBuffer());
        }
        return Ux.toFuture(this);
    }

    /* Package Invoke by ScSession */
    Future<JsonObject> storeProfile(final JsonObject authority) {
        this.session.put("profile", authority.toBuffer());
        return Future.succeededFuture(authority);
    }
}
