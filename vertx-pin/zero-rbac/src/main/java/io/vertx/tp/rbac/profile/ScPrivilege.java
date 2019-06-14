package io.vertx.tp.rbac.profile;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.tp.rbac.refine.Sc;
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
        return new ScPrivilege(sessionId).open()
                .compose(self -> self.storeData(data));
    }

    public static Future<ScPrivilege> open(final String sessionId) {
        return new ScPrivilege(sessionId).open();
    }

    private Future<ScPrivilege> open() {
        return Sc.session(this.sessionId).compose(session -> {
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

    Future<JsonObject> storeProfile(final JsonObject authority) {
        this.session.put("profile", authority.toBuffer());
        return Future.succeededFuture(authority);
    }

    public JsonObject getProfile() {
        final Buffer profile = this.session.get("profile");
        if (Objects.nonNull(profile)) {
            return profile.toJsonObject();
        } else {
            return new JsonObject();
        }
    }

    public Future<JsonObject> getProfileAsync() {
        return Ux.toFuture(this.getProfile());
    }
}
