package up.god.micro.request;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class OneWayWorker {

    @Address("ZERO://ONE-WAY")
    public void process(final Message<Envelop> message) {
        final Envelop input = message.body();
        final JsonObject requestData = input.data();
        System.out.println(requestData);
        message.reply(Envelop.success(new JsonObject().put("Result", "Hello")));
    }
}
