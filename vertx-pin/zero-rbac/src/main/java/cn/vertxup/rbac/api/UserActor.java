package cn.vertxup.rbac.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.commune.Envelop;

@Queue
public class UserActor {
    /*
     * User information get from the system to extract data here.
     */
    @Address(Addr.User.INFORMATION)
    public Future<JsonObject> information(final Envelop envelop) {
        final JsonObject logged = envelop.user().principal();
        System.err.println(logged);
        return Future.succeededFuture();
    }
}
