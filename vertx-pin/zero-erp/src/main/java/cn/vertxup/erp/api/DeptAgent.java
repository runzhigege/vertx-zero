package cn.vertxup.erp.api;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.erp.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.eon.ID;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;

@EndPoint
public interface DeptAgent {

    @Path("/api/depts")
    @GET
    @Address(Addr.Dept.DEPT_KEY)
    JsonObject fetchDepts(@HeaderParam(ID.Header.X_SIGMA) String sigma);

}
