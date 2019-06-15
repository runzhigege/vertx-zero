package io.vertx.tp.rbac.atom;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.authorization.ScPrivilege;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.up.aiki.Ux;
import io.vertx.up.eon.ID;

import java.io.Serializable;

public class ScRequest implements Serializable {

    private static final ScConfig CONFIG = ScPin.getConfig();

    private transient final String uri;
    private transient final String key;
    private transient final String requestUri;
    private transient final String sigma;
    private transient final String user;
    private transient final String sessionId;
    private transient final HttpMethod method;

    public ScRequest(final JsonObject data) {
        final JsonObject metadata = data.getJsonObject(AuthKey.F_METADATA);
        this.uri = metadata.getString(AuthKey.F_URI);
        this.requestUri = metadata.getString(AuthKey.F_URI_REQUEST);
        this.method = HttpMethod.valueOf(metadata.getString(AuthKey.F_METHOD));
        /*
         * Extension for orbit
         */
        this.key = ScUri.getUriId(this.uri, this.requestUri);
        /*
         * Support multi applications
         */
        if (CONFIG.getSupportMultiApp()) {
            final JsonObject headers = data.getJsonObject(AuthKey.F_HEADERS);
            this.sigma = headers.getString(ID.Header.X_SIGMA);
        } else {
            this.sigma = null;
        }
        /*
         * Token extract
         */
        final String token = data.getString("jwt");
        final JsonObject userData = Ux.Jwt.extract(token);
        this.user = userData.getString("user");
        this.sessionId = userData.getString("session");
    }

    public String getNormalizedUri() {
        return this.key;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public String getSigma() {
        return this.sigma;
    }

    public String getUser() {
        return this.user;
    }

    private Future<JsonObject> asyncProfile() {
        return ScPrivilege.open(this.sessionId)
                .compose(ScPrivilege::getProfileAsync);
    }

    public Future<JsonArray> asyncPermission(final String profileKey) {
        return this.asyncProfile()
                .compose(profile -> Ux.toFuture(profile.getJsonObject(profileKey)))
                .compose(single -> Ux.toFuture(single.getJsonArray(AuthKey.PROFILE_PERM)));
    }

    public Future<JsonArray> asyncRole(final String profileKey) {
        return this.asyncProfile()
                .compose(profile -> Ux.toFuture(profile.getJsonObject(profileKey)))
                .compose(single -> Ux.toFuture(single.getJsonArray(AuthKey.PROFILE_ROLE)));
    }
}
