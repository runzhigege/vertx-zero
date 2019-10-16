package cn.vertxup.jet.api;

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
    String startJob(@PathParam("name") String name);

    @Path("/job/stop/{name}")
    @PUT
    @Address(JtAddr.Job.STOP)
    String stopJob(@PathParam("name") String name);

    @Path("/job/resume/{name}")
    @PUT
    @Address(JtAddr.Job.RESUME)
    String resumeJob(@PathParam("name") String name);

    @Path("/job/info/name/{name}")
    @GET
    @Address(JtAddr.Job.STATUS)
    String statusJob(@PathParam("name") String name);

    @Path("/job/info/by/sigma")
    @GET
    @Address(JtAddr.Job.BY_SIGMA)
    String fetchJobs(@HeaderParam(ID.Header.X_SIGMA) String sigma);
}
