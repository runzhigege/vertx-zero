package up.god.test;

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@EndPoint
@Path("/api")
public interface ParamApi {

    @Path("/test/params")
    @GET
    @Address("TEST://EVENT")
    String direct(@QueryParam("first") String first,
                  @QueryParam("second") String second);

    @Path("/test/params1")
    @GET
    @Address("TEST://EVENT1")
    String onlyone(@QueryParam("first") String first);
}
