package up.god.test;

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@EndPoint
@Path("/api")
public interface ParamApi {

    @Path("/{appId}/page")
    @GET
    @Address("TEST://EVENT")
    String direct(@PathParam("appId") String first,
                  @QueryParam("uri") String second);

    @Path("/test/params1")
    @GET
    @Address("TEST://EVENT1")
    String onlyone(@QueryParam("first") String first);

    @Path("/test/params")
    @GET
    @Address("TEST://EVENT3")
    String direct1(@QueryParam("first") String first,
                   @QueryParam("second") String second);
}
