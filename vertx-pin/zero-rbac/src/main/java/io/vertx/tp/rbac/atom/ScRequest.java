package io.vertx.tp.rbac.atom;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeDefault;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.tp.rbac.permission.ScPrivilege;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.eon.ID;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;

import java.io.Serializable;

public class ScRequest implements Serializable {

    private static final ScConfig CONFIG = ScPin.getConfig();
    private static final Annal LOGGER = Annal.get(ScRequest.class);

    private transient final String uri;
    private transient final String requestUri;
    private transient final String sigma;
    private transient final String user;
    private transient final String sessionId;
    private transient final String view = KeDefault.VIEW_DEFAULT;
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
        requestUri = metadata.getString(AuthKey.F_URI_REQUEST);
        method = HttpMethod.valueOf(metadata.getString(AuthKey.F_METHOD));
        /*
         * Extension for orbit
         */
        this.uri = Sc.uri(uri, requestUri);
        /*
         * Support multi applications
         */
        if (CONFIG.getSupportMultiApp()) {
            final JsonObject headers = data.getJsonObject(AuthKey.F_HEADERS);
            sigma = headers.getString(ID.Header.X_SIGMA);
        } else {
            sigma = null;
        }
        /*
         * Token analyze
         */
        final String token = data.getString("jwt");
        final JsonObject userData = Ux.Jwt.extract(token);
        user = userData.getString("user");
        sessionId = userData.getString(KeField.HABITUS);
    }

    public String getNormalizedUri() {
        return uri;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getSigma() {
        return sigma;
    }

    public String getUser() {
        return user;
    }

    public String getView() {
        return view;
    }

    public String getCacheKey() {
        return Ke.keySession(method.name(), uri);
    }

    public String getAuthorizedKey() {
        return Ke.keyAuthorized(method.name(), uri);
    }

    public String getSessionId() {
        return sessionId;
    }

    public Future<ScPrivilege> openSession() {
        LOGGER.info("Open session: {0}", sessionId);
        return ScPrivilege.open(sessionId);
    }
}
