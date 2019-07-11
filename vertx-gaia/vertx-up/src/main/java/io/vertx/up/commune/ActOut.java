package io.vertx.up.commune;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.unity.Ux;
import io.vertx.up.atom.Envelop;

import java.io.Serializable;

public class ActOut implements Serializable {

    private transient final Envelop envelop;

    public ActOut(final Object data) {
        this.envelop = Envelop.success(data);
    }

    public ActOut(final Throwable ex) {
        this.envelop = Envelop.failure(ex);
    }

    public static Future<ActOut> future() {
        return Ux.toFuture(new ActOut(new JsonObject()));
    }

    public Envelop sync() {
        return this.envelop;
    }

    public Future<Envelop> async() {
        return this.envelop.toFuture();
    }
}
