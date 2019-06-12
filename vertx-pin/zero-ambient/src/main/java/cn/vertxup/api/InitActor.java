package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.cv.Addr;
import io.vertx.tp.ambient.service.InitStub;
import io.vertx.tp.ke.tool.Ke;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.zero.atom.Database;
import io.zero.epic.Ut;

import javax.inject.Inject;

@Queue
public class InitActor {

    @Inject
    private transient InitStub stub;

    @Address(Addr.Init.INIT)
    public Future<JsonObject> initApp(final String appId, final JsonObject data) {
        return this.stub.initApp(appId, data);
    }

    @Address(Addr.Init.PREPARE)
    public Future<JsonObject> prepare(final String appName) {
        return this.stub.prerequisite(appName);
    }

    @Address(Addr.Init.CONNECT)
    public JsonObject connect(final JsonObject data) {
        final Database database = Ut.deserialize(data, Database.class);
        final boolean checked = database.test();
        return Ke.Result.bool(checked);
    }
}
