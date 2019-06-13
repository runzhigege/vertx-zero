package io.vertx.tp.rbac.atom;

import io.vertx.tp.ke.extension.Orbit;
import io.vertx.tp.rbac.init.ScPin;

/*
 * Uri Processing to calculate resource key here.
 */
class ScUri {

    static String getUriId(final String uri, final String requestUri) {
        final Orbit orbit = ScPin.getOrbit();
        if (null == orbit) {
            return uri;
        } else {
            return orbit.extract(uri, requestUri);
        }
    }
}
