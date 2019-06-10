package up.zero.service;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
@Path("/api")
public class DemoApi {


    @Path("/test/skip/crud")
    @GET
    public JsonObject test() {

        return new JsonObject().put("test", "Hello");
    }
}
