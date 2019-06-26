package cn.vertxup.api;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*
 * Export / Import file here for processing
 */
@EndPoint
@Path("/api")
public class FileAgent {

    @Path("/{actor}/import")
    @POST
    @Address(Addr.File.IMPORT)
    public JsonObject importFile(@PathParam("actor") final String actor,
                                 @StreamParam final FileUpload fileUpload) {
        System.out.println("Import " + actor);
        return null;
    }

    @Path("/{actor}/export")
    @POST
    @Address(Addr.File.EXPORT)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public JsonObject exportFile(@PathParam("actor") final String actor,
                                 @BodyParam final JsonArray columns) {

        System.out.println("Export " + actor);
        return null;
    }
}
