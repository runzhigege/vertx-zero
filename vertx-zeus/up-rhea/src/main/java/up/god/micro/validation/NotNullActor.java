package up.god.micro.validation;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@EndPoint
@Path("/api/jsr303/")
public class NotNullActor {

    @GET
    @Path("/notnull/query")
    public String testValid(@PathParam("name") final String name) {
        return "Hello, " + name;
    }
}
