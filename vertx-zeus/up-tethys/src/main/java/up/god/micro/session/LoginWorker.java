package up.god.micro.session;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class LoginWorker {

    public Future<JsonObject> secure(final Envelop envelop) {
        return Future.succeededFuture(new JsonObject().put("_id", "Lang"));
    }
}
