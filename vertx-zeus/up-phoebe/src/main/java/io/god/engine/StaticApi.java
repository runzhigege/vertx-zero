package io.god.engine;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
public class StaticApi {

    @Path("/api/test")
    @GET
    public String test() {
        return "Hello World";
    }
}
