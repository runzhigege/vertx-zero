package cn.vertxup.api;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/*
 * HTTP Method: Put
 */
@EndPoint
@Path("/api")
public interface PutAgent {
    @PUT
    @Path("/{actor}/{key}")
    @Address(Addr.Put.BY_ID)
    JsonObject update(@PathParam("actor") String actor,
                      @PathParam("key") String key,
                      @BodyParam JsonObject data);
}
