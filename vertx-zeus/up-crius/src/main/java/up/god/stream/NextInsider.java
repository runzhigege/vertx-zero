package up.god.stream;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class NextInsider {
    @Ipc(value = "IPC://TWO/NEXT", to = "IPC://TWO/THIRD", name = "ipc-cronus")
    public JsonObject next(final Envelop envelop) {
        final String content = envelop.data();
        return new JsonObject(content).put("coordinator2", "ipc-cronus");
    }

}
