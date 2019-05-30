package io.vertx.up.secure.provider;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.secure.Security;

/**
 * JwtAuthProvider will call JwtSecurer here
 */
class JwtSecurer {

    private final transient Security security;

    private JwtSecurer(final Security security) {
        this.security = security;
    }

    static JwtSecurer create(final Security security) {
        return new JwtSecurer(security);
    }

    /*
     * 401
     */
    Future<Boolean> authenticate(final JsonObject authInfo) {
        return this.security.verify(authInfo);
    }

    /*
     * 403
     */
    Future<Boolean> authorize(final JsonObject authInfo) {
        return this.security.access(authInfo);
    }
}
