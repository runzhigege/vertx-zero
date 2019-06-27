package cn.vertxup.api;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Codex;
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
                                 @StreamParam @Codex final FileUpload fileUpload) {
        System.out.println("Import " + actor);
        return null;
    }

    @Path("/{actor}/export")
    @POST
    @Address(Addr.File.EXPORT)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public JsonObject exportFile(@PathParam("actor") final String actor,
                                 @BodyParam final JsonObject condition) {
        /* Exported columns here for future calculation */
        final JsonArray columns = condition.getJsonArray("columns");
        /* Remove columns here and set criteria as condition */
        final JsonObject query = Uson.create(condition).remove("columns").to();
        /*
         * Toggle format here
         * {
         *     "0": xxx,
         *     "1": yyy,
         *     "2": zzz,
         *     ......
         * }
         */
        return Ux.toToggle(actor, query, columns);
    }
}
