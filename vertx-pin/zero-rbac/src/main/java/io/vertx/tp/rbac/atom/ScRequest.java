package io.vertx.tp.rbac.atom;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.up.aiki.Ux;
import io.vertx.up.eon.ID;

import java.io.Serializable;

public class ScRequest implements Serializable {

    private static final ScConfig CONFIG = ScPin.getConfig();

    private transient final String uri;
    private transient final String requestUri;
    private transient final String sigma;
    private transient final String user;
    private transient final String sessionId;
    private transient final String view = AuthKey.VIEW_DEFAULT;
    private transient final HttpMethod method;

    /*
     * {
     *     "metadata" : {
     *         "uri" : "xxx",
     *         "requestUri" : "xxx",
     *         "method" : "GET"
     *     },
     *     "jwt" : "xxxxx",
     *     "headers" : {
     *         "X-Sigma" : "xxx"
     *     },
     *     "options" : { }
     * }
     */
    public ScRequest(final JsonObject data) {
        final JsonObject metadata = data.getJsonObject(AuthKey.F_METADATA);
        final String uri = metadata.getString(AuthKey.F_URI);
        this.requestUri = metadata.getString(AuthKey.F_URI_REQUEST);
        this.method = HttpMethod.valueOf(metadata.getString(AuthKey.F_METHOD));
        /*
         * Extension for orbit
         */
        this.uri = ScUri.getUriId(uri, this.requestUri);
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
        return this.uri;
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

    public String getView() {
        return this.view;
    }

    public String getCacheKey() {
        return "session-" + this.method.name() + ":" + this.uri;
    }

    public String getAuthorizedKey() {
        return "authorized-" + this.method.name() + ":" + this.uri;
    }

    public Future<ScPrivilege> openSession() {
        return ScPrivilege.open(this.sessionId);
    }
}
