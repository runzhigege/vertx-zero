package up.god.micro.session;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.ContextParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("")
@EndPoint
public class LoginActor {

    @POST
    @Path("/login")
    public JsonObject login(@BodyParam final JsonObject data,
                            @ContextParam("key") final String hello,
                            final Session session) {
        System.out.println(session.data());
        System.out.println(hello);
        return new JsonObject().put("_id", "Lang");
    }
}
