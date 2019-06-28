package io.vertx.up.commune;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;

import java.io.Serializable;

public class ActResponse implements Serializable {

    private transient final Envelop envelop;

    public ActResponse(final Object data) {
        this.envelop = Envelop.success(data);
    }

    public ActResponse(final Throwable ex) {
        this.envelop = Envelop.failure(ex);
    }

    public Envelop sync() {
        return this.envelop;
    }

    public Future<Envelop> async() {
        return this.envelop.toFuture();
    }
}
