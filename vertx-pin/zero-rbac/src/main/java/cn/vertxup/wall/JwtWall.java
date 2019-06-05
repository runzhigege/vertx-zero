package cn.vertxup.wall;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.tp.rbac.service.jwt.JwtStub;
import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Wall;
import io.vertx.up.log.Annal;
import io.vertx.up.secure.Security;
import io.vertx.up.secure.handler.JwtOstium;
import io.vertx.up.secure.provider.JwtAuth;

import javax.inject.Inject;

@Wall(value = "jwt", path = "/api/*")
public class JwtWall implements Security {
    private static final Annal LOGGER = Annal.get(JwtWall.class);
    @Inject
    private transient JwtStub stub;

    @Authenticate
    public AuthHandler authenticate(final Vertx vertx,
                                    final JsonObject config) {
        return JwtOstium.create(JwtAuth.create(vertx, new JWTAuthOptions(config))
                .bind(() -> this));
    }

    @Override
    public Future<JsonObject> store(final JsonObject data) {
        final String userKey = data.getString("user");
        Sc.infoAuth(LOGGER, AuthMsg.TOKEN_STORE, userKey);
        return this.stub.store(userKey, data);
    }

    @Override
    public Future<Boolean> verify(final JsonObject data) {

        return Future.succeededFuture(Boolean.TRUE);
    }
}
