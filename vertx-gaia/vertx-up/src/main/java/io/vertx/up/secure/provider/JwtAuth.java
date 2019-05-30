package io.vertx.up.secure.provider;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.up.secure.Security;

public interface JwtAuth extends AuthProvider {


    static JwtAuth create(final Vertx vertx,
                          final JWTAuthOptions config) {
        return new JwtAuthProvider(vertx, config);
    }

    JwtAuth bind(Security security);

    String generateToken(JsonObject data, JWTOptions options);

    default String generateToken(final JsonObject claims) {
        return this.generateToken(claims, new JWTOptions());
    }
}
