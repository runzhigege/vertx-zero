package up.god.micro;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
@Path("/api")
public class DataAgent {

    @GET
    @Path("/event")
    @Address("ZERO://EVENT")
    public JsonObject testRpc(){
        return new JsonObject().put("params","Lang");
    }
}
