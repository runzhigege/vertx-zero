package cn.vertxup.ui.api;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.ui.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.eon.ID;

import javax.ws.rs.BodyParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/api")
public interface PageApi {
    /*
     * Condition should be:
     * sigma +
     * {
     *     app,
     *     module,
     *     page
     * }
     * AMP means three parameters here.
     */
    @Path("/ui/page")
    @POST
    @Address(Addr.Page.FETCH_AMP)
    JsonObject fetchPage(@HeaderParam(ID.Header.X_SIGMA) String sigma,
                         @BodyParam JsonObject body);
}
