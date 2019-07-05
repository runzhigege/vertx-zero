package cn.vertxup.api;

import io.vertx.tp.jet.cv.JtAddr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/api-debug")
@EndPoint
public interface JobApi {
    @Path("/job/start/{name}")
    @POST
    @Address(JtAddr.Job.START)
    String startJob(@PathParam("name") String name);

    @Path("/job/stop/{name}")
    @POST
    @Address(JtAddr.Job.STOP)
    String stopJob(@PathParam("name") String name);

    @Path("/job/resume/{name}")
    @POST
    @Address(JtAddr.Job.RESUME)
    String resumeJob(@PathParam("name") String name);

    @Path("/job/status/{name}")
    @GET
    @Address(JtAddr.Job.STATUS)
    String statusJob(@PathParam("name") String name);
}
