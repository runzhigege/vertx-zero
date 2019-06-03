package cn.vertxup.api;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/*
 * Http Method: Get
 */
@EndPoint
@Path("/api")
public interface GetAgent {

    @GET
    @Path("/{actor}/{key}")
    @Address(Addr.Get.BY_ID)
    JsonObject getById(@PathParam("actor") String actor,
                       @PathParam("key") String key);

    @GET
    @Path("/columns/full/{actor}")
    @Address(Addr.Get.COLUMN_FULL)
    JsonArray getFull(@PathParam("actor") String actor);

    @GET
    @Path("/columns/my/{actor}")
    @Address(Addr.Get.COLUMN_MY)
    JsonArray getMy(@PathParam("actor") String actor);
}
