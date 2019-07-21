package cn.vertxup.rbac.api;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
@Path("/api")
public interface UserAgent {
    /*
     * /api/user
     * Request: get user id from token
     */
    @GET
    @Path("user")
    @Address(Addr.User.INFORMATION)
    JsonObject information();
}
