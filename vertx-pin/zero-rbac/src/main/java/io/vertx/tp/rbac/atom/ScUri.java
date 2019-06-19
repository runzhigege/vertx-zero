package io.vertx.tp.rbac.atom;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.ke.extension.Orbit;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.up.web.ZeroAnno;

/*
 * Uri Processing to calculate resource key here.
 */
public class ScUri {

    static String getUriId(final String uri, final String requestUri) {
        final Orbit orbit = ScPin.getOrbit();
        if (null == orbit) {
            return uri;
        } else {
            return orbit.extract(uri, requestUri);
        }
    }

    public static String getUriId(final RoutingContext context) {
        final HttpServerRequest request = context.request();
        final HttpMethod method = request.method();
        final String requestUri = ZeroAnno.recoveryUri(request.uri(), method);
        return getUriId(requestUri, request.uri());
    }
}
