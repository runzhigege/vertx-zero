package io.zero.epic.container;

import io.vertx.core.Future;
import io.zero.epic.fn.Fn;

/**
 * Single Rxjava container for null pointer that is not used in
 * Rxjava2.
 */
@SuppressWarnings("all")
public class RxHod {

    private Object reference;

    public <T> RxHod add(final T reference) {
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
