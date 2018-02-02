package up.god.micro;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;

import javax.inject.Inject;

@Queue
public class DataWorker {
    @Inject
    private transient DataInject injector;

    @Address("ZERO://EVENT")
    public Future<JsonObject> callRpc(final JsonObject params) {
        return Ux.thenRpc("ipc-hecate", "IPC://EVENT/DATA", params);
    }
}
