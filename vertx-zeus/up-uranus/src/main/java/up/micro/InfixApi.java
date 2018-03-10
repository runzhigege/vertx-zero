package up.micro;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.ali.sms.SmsClient;
import io.vertx.tp.plugin.qiy.QiyClient;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.annotations.Plugin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
public class InfixApi {

    @Plugin
    private QiyClient qiyClient;

    @Plugin
    private SmsClient smsClient;

    @Path("/api/say")
    @GET
    public JsonObject say() {
        System.out.println(this.qiyClient);
        System.out.println(this.smsClient);
        return new JsonObject();
    }
}
