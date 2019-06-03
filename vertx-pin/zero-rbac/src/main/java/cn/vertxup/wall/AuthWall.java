package cn.vertxup.wall;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Wall;
import io.vertx.up.secure.Security;
import io.vertx.up.secure.handler.JwtOstium;
import io.vertx.up.secure.provider.JwtAuth;

@Wall(value = "jwt", path = "/api/*")
public class AuthWall implements Security {
    @Authenticate
    public AuthHandler authenticate(final Vertx vertx,
                                    final JsonObject config) {
        return JwtOstium.create(JwtAuth.create(vertx, new JWTAuthOptions(config))
                .bind(() -> this));
    }

    @Override
    public Future<JsonObject> store(final JsonObject data) {
        System.out.println(data);
        return Future.succeededFuture();
    }

    @Override
    public Future<Boolean> verify(final JsonObject data) {

        return Future.succeededFuture(Boolean.TRUE);
    }
}
