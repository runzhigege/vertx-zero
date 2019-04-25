package up.god.test;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;

@Queue
public class ParamWorker {

    @Address("TEST://EVENT")
    public Future<JsonObject> direct(final String first, final String second) {
        return Future.succeededFuture(new JsonObject().put("first", first)
                .put("second", second));
    }

    @Address("TEST://EVENT3")
    public Future<JsonObject> direct1(final String first, final String second) {
        return Future.succeededFuture(new JsonObject().put("first", first)
                .put("second", second));
    }

    @Address("TEST://EVENT1")
    public Future<JsonObject> onlyOne(final String first) {
        return Future.succeededFuture(new JsonObject().put("first", first));
    }

    @Address("TEST://EVENT2")
    public JsonObject direct1(final JsonObject params) {
        return params;
    }
}
