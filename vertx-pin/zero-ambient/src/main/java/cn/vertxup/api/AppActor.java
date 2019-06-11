package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.cv.Addr;
import io.vertx.tp.ambient.service.AppStub;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;

import javax.inject.Inject;

@Queue
public class AppActor {

    @Inject
    private transient AppStub appStub;

    @Address(Addr.App.BY_NAME)
    public Future<JsonObject> byName(final String name) {
        return this.appStub.fetchByName(name);
    }
}
