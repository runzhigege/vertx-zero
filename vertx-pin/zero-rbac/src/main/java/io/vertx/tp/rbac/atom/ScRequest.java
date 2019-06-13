package io.vertx.tp.rbac.atom;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.extension.Orbit;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.init.ScPin;

import java.io.Serializable;
import java.util.Objects;

public class ScRequest implements Serializable {

    private transient final String uri;
    private transient final String key;
    private transient final String requestUri;
    private transient final HttpMethod method;

    public ScRequest(final JsonObject data) {
        this.uri = data.getString(AuthKey.F_URI);
        this.requestUri = data.getString(AuthKey.F_URI_REQUEST);
        this.method = HttpMethod.valueOf(data.getString(AuthKey.F_METHOD));
        /*
         * Extension for orbit
         */
        final Orbit orbit = ScPin.getOrbit();
        if (Objects.isNull(orbit)) {
            this.key = this.uri;
        } else {
            this.key = orbit.extract(this.uri, this.requestUri);
        }
    }

    public String getNormalizedUri() {
        return this.key;
    }

    public HttpMethod getMethod() {
        return this.method;
    }
}
