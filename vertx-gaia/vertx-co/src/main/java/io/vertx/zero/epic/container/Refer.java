package io.vertx.zero.epic.container;

import io.vertx.core.Future;
import io.vertx.zero.fn.Fn;

/**
 * Single Rxjava container for null pointer that is not used in
 * Rxjava2.
 */
@SuppressWarnings("all")
public final class Refer {

    private Object reference;

    public <T> Refer add(final T reference) {
        this.reference = reference;
        return this;
    }

    /*
     * For vert.x compose method only.
     */
    public <T> Future<T> future(final T reference) {
        this.reference = reference;
        return Future.succeededFuture(reference);
    }

    public boolean successed() {
        return null != this.reference;
    }

    @SuppressWarnings("unchecked")
    public <T> T get() {
        return Fn.getNull(() -> (T) this.reference, this.reference);
    }
}
