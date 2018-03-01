package up.wall;

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

@Wall(value = "jwt", path = "/api/secure/*")
public class JwtWall implements Security {
    private static JwtAuth AUTH = null;

    @Authenticate
    public AuthHandler authenticate(final Vertx vertx,
                                    final JsonObject config) {
        if (null == AUTH) {
            AUTH = JwtAuth.create(vertx, new JWTAuthOptions(config), this::verify);
        }
        return JwtOstium.create(AUTH);
    }

    @Override
    public JwtAuth get() {
        return AUTH;
    }

    @Override
    public JsonObject store(final JsonObject data) {
        System.out.println(data);
        final String token = this.get().generateToken(data);
        System.out.println(token);
        return data.put("token", token);
    }

    @Override
    public Future<JsonObject> asyncStore(final JsonObject data) {
        return Future.succeededFuture(this.store(data));
    }

    @Override
    public Future<Boolean> verify(final JsonObject data) {
        System.out.println(data);
        return Future.succeededFuture(Boolean.FALSE);
    }
}
