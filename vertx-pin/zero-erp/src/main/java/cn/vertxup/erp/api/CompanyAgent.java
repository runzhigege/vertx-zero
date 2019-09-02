package cn.vertxup.erp.api;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.erp.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.eon.ID;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/*
 * Company Api information
 */
@EndPoint
@Path("/api")
public interface CompanyAgent {
    /*
     * /api/company/employee/:eid
     * Request: get company information by user id
     */
    @GET
    @Path("company/employee/:eid")
    @Address(Addr.Company.INFORMATION)
    JsonObject company(@PathParam("eid") String eid);

    @Path("companys")
    @GET
    @Address(Addr.Company.COMPANY_KEY)
    JsonObject fetchCompanys(@HeaderParam(ID.Header.X_SIGMA) String sigma);
}
