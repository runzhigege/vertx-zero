package io.vertx.up.secure.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.handler.AuthHandler;

import java.util.List;

public interface JwtOstium extends AuthHandler {
    static JwtOstium create(final JWTAuth authProvider) {
        return new JwtPhylum(authProvider, (String) null);
    }

    static JwtOstium create(final JWTAuth authProvider, final String skip) {
        return new JwtPhylum(authProvider, skip);
    }

    @Fluent
    JwtOstium setAudience(List<String> var1);

    @Fluent
    JwtOstium setIssuer(String var1);

    @Fluent
    JwtOstium setIgnoreExpiration(boolean var1);
}
