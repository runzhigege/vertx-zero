package up.god.stream;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class NextInsider {
    @Ipc(value = "IPC://TWO/NODE", to = "IPC://TWO/NEXT", name = "ipc-crius")
    public String next(final Envelop envelop) {
        final JsonObject data = envelop.data();
        data.put("coordinator1", "ipc-coeus");
        return data.encode();
    }
}
