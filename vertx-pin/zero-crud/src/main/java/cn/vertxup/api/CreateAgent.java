package cn.vertxup.api;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Codex;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/*
 * HTTP Method: POST
 */
@EndPoint
@Path("/api")
public interface CreateAgent {
    /*
     * Pure Creating for different entity
     */
    @POST
    @Path("/{actor}")
    @Address(Addr.Post.ADD)
    JsonObject create(@PathParam("actor") String actor,
                      @BodyParam @Codex JsonObject data);
}
