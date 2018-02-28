package up.god.micro.session;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.annotations.SessionData;

import javax.ws.rs.BodyParam;
import javax.ws.rs.ContextParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("")
@EndPoint
public class LoginActor {

    @POST
    @Path("/login")
    @SessionData(value = "user", field = "_id")
    public JsonObject login(@BodyParam final JsonObject data,
                            @ContextParam("key") final String hello,
                            final Session session) {
        System.out.println(session.data());
        System.out.println(hello);
        return new JsonObject().put("_id", "Lang");
    }

    @POST
    @Path("/login-message")
    @SessionData(value = "user", field = "_id")
    @Address("ZERO://SECURE/MSG")
    public JsonObject loginMessage(@BodyParam final JsonObject data,
                                   final Session session) {
        System.out.println(session.data());
        System.out.println(data);
        return new JsonObject();
    }
}
