package up.god.ipc;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class HelloInsider {

    @Ipc("ZERO://RPC/FIRST")
    public Envelop sayHello(final Envelop envelop) {
        final JsonObject data = envelop.data();
        System.out.println(data);
        return Envelop.success(data);
    }
}
