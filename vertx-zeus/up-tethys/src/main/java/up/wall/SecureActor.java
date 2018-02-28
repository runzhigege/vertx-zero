package up.wall;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Wall;
import io.vertx.up.secure.handler.JwtOstium;

@Wall(value = "jwt", path = "/api/*")
public class SecureActor {
    @Authenticate
    public AuthHandler authenticate(final Vertx vertx,
                                    final JsonObject config) {
        return JwtOstium.create(
                JWTAuth.create(vertx, new JWTAuthOptions(config))
        );
    }
}
