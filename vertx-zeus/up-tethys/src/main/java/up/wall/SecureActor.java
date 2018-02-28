package up.wall;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.mongo.MongoAuth;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Wall;
import io.vertx.up.plugin.mongo.MongoInfix;
import io.vertx.up.secure.handler.BasicOstium;

@Wall(value = "mongo", path = "/api/*")
public class SecureActor {
    @Authenticate
    public AuthHandler authenticate(final JsonObject config) {
        return BasicOstium.create(
                MongoAuth.create(MongoInfix.getClient(), config)
        );
    }

}
