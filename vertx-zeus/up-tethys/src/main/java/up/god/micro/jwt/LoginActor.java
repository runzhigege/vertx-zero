package up.god.micro.jwt;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.secure.Security;

import javax.inject.Inject;
import javax.ws.rs.BodyParam;
import javax.ws.rs.ContextParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api")
@EndPoint
public class LoginActor {

    @Inject
    private transient Security security;

    @POST
    @Path("/login")
    public JsonObject login(@BodyParam final JsonObject data,
                            @ContextParam("key") final String hello) {
        data.put("sub", "paulo");
        this.security.store(data);
        return data;
    }

    @POST
    @Path("/secure/jwt")
    public JsonObject secure(@BodyParam final JsonObject data) {
        return new JsonObject().put("jwt", "test");
    }
}
