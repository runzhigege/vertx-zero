package up.god.micro.request;

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class OneWayWorker {

    @Address("ZERO://ONE-WAY")
    public void process(final Envelop envelop) {
        final String item = envelop.data();
        System.out.println(item);
    }
}
