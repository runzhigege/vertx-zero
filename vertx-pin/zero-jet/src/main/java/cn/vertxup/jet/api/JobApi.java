package cn.vertxup.jet.api;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtAddr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.eon.ID;

import javax.ws.rs.*;

@Path("/api")
@EndPoint
public interface JobApi {
    @Path("/job/start/{name}")
    @PUT
    @Address(JtAddr.Job.START)
    String startJob(@HeaderParam(ID.Header.X_SIGMA) String sigma, @PathParam("name") String name);

    @Path("/job/stop/{name}")
    @PUT
    @Address(JtAddr.Job.STOP)
    String stopJob(@HeaderParam(ID.Header.X_SIGMA) String sigma, @PathParam("name") String name);

    @Path("/job/resume/{name}")
    @PUT
    @Address(JtAddr.Job.RESUME)
    String resumeJob(@HeaderParam(ID.Header.X_SIGMA) String sigma, @PathParam("name") String name);

    @Path("/job/info/name/{name}")
    @GET
    @Address(JtAddr.Job.STATUS)
    String statusJob(@HeaderParam(ID.Header.X_SIGMA) String sigma, @PathParam("name") String name);

    @Path("/job/info/by/sigma")
    @GET
    @Address(JtAddr.Job.BY_SIGMA)
    String fetchJobs(@HeaderParam(ID.Header.X_SIGMA) String sigma);

    @Path("/job/info/fetch/:key")
    @GET
    @Address(JtAddr.Job.GET_BY_KEY)
    String fetchJob(@PathParam("key") String key);

    @Path("/job/info/update/:key")
    @PUT
    @Address(JtAddr.Job.UPDATE_BY_KEY)
    String updateJob(@PathParam("key") String key,
                     @BodyParam JsonObject data);

}
