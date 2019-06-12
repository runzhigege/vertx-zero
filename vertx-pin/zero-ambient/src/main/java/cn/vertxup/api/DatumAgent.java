package cn.vertxup.api;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.eon.ID;

import javax.ws.rs.*;

@EndPoint
@Path("/api")
public interface DatumAgent {

    @Path("/categories/type/{type}")
    @GET
    @Address(Addr.Datum.CATEGORY_TYPE)
    JsonArray categoryByType(@HeaderParam(ID.Header.X_APP_ID) String appId,
                             @PathParam("type") String type);

    @Path("/categories/by-types")
    @POST
    @Address(Addr.Datum.CATEGORY_TYPES)
    JsonObject fetchCategories(@HeaderParam(ID.Header.X_APP_ID) String appId,
                               @BodyParam JsonArray types);

    @Path("/category/{type}/{code}")
    @GET
    @Address(Addr.Datum.CATEGORY_CODE)
    JsonArray fetchCategory(@HeaderParam(ID.Header.X_APP_ID) String appId,
                            @PathParam("type") String type,
                            @PathParam("code") String code);

    @Path("/tabulars/type/{type}")
    @GET
    @Address(Addr.Datum.TABULAR_TYPE)
    JsonArray tabularByType(@HeaderParam(ID.Header.X_APP_ID) String appId,
                            @PathParam("type") String type);

    @Path("/tabulars/by-types")
    @POST
    @Address(Addr.Datum.TABULAR_TYPES)
    JsonObject fetchTabulars(@HeaderParam(ID.Header.X_APP_ID) String appId,
                             @BodyParam JsonArray types);

    @Path("/tabular/{type}/{code}")
    @GET
    @Address(Addr.Datum.TABULAR_CODE)
    JsonArray fetchTabular(@HeaderParam(ID.Header.X_APP_ID) String appId,
                           @PathParam("type") String type,
                           @PathParam("code") String code);


}
