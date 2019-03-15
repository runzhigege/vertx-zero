package up.god.test;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@EndPoint
@Path("/api")
public class ParamAgent {
    @Path("/test/params2")
    @GET
    @Address("TEST://EVENT2")
    public JsonObject direct(@QueryParam("first") final String first,
                             @QueryParam("second") final String second) {
        return new JsonObject().put("first", first)
                .put("second", second);
    }
}
