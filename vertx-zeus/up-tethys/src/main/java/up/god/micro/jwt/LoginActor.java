package up.god.micro.jwt;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.secure.Security;
import io.vertx.up.tool.mirror.Instance;
import up.wall.SecureActor;

import javax.ws.rs.BodyParam;
import javax.ws.rs.ContextParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api")
@EndPoint
public class LoginActor {

    @POST
    @Path("/login")
    public JsonObject login(@BodyParam final JsonObject data,
                            @ContextParam("key") final String hello) {
        final Security security = Instance.singleton(SecureActor.class);
        security.store(data);
        return new JsonObject().put("_id", "Lang");
    }

    @POST
    @Path("/secure/jwt")
    public JsonObject secure(@BodyParam final JsonObject data) {
        return new JsonObject().put("jwt", "test");
    }
}
