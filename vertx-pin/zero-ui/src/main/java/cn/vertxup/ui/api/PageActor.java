package cn.vertxup.ui.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ui.cv.Addr;
import io.vertx.tp.ui.service.PageStub;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;

import javax.inject.Inject;

@Queue
public class PageActor {

    @Inject
    private transient PageStub stub;

    @Address(Addr.Page.FETCH_AMP)
    public Future<JsonObject> fetchAmp(final String sigma,
                                       final JsonObject body) {
        return this.stub.fetchAmp(sigma, body);
    }
}
