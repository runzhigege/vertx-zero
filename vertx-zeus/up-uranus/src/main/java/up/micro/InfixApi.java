package up.micro;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.plugin.shared.SharedClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
public class InfixApi {

    @Plugin
    private SharedClient<String, Object> sharedClient;

    @Path("/api/say")
    @GET
    public JsonObject say() {
        this.sharedClient.put("key2", "Lang", 5, res -> {
            System.out.println(res.result());
        });
        return new JsonObject();
    }

    @Path("/api/get")
    @GET
    public JsonObject sayKey() {
        this.sharedClient.get("key", res -> {
            System.out.println(res.result());
        });
        return new JsonObject();
    }
}
