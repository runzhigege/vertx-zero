package up.god.test;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;

@Queue
public class ParamWorker {

    @Address("TEST://EVENT")
    public JsonObject direct(final String first, final String second) {
        return new JsonObject().put("first", first)
                .put("second", second);
    }

    @Address("TEST://EVENT1")
    public JsonObject onlyOne(final String first) {
        return new JsonObject().put("first", first);
    }

    @Address("TEST://EVENT2")
    public JsonObject direct1(final JsonObject params) {
        return params;
    }
}
