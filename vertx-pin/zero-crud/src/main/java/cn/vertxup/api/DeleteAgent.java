package cn.vertxup.api;

import io.vertx.core.json.JsonArray;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/*
 * Http Method: DELETE
 */
@EndPoint
@Path("/api")
public interface DeleteAgent {
    @DELETE
    @Path("/{actor}/{key}")
    @Address(Addr.Delete.BY_ID)
    Boolean delete(@PathParam("actor") String actor,
                   @PathParam("key") String key);

    @DELETE
    @Path("/batch/{actor}/delete")
    @Address(Addr.Delete.BATCH)
    Boolean deleteBatch(@PathParam("actor") String actor,
                        @BodyParam JsonArray data);
}
