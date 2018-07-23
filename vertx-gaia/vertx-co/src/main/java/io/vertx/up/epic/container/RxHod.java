package io.vertx.up.epic.container;

import io.vertx.up.epic.fn.Fn;

/**
 * Single Rxjava container for null pointer that is not used in
 * Rxjava2.
 */
public class RxHod {

    private Object reference;

    public <T> RxHod add(final T reference) {
        this.reference = reference;
        return this;
    }

    public boolean successed() {
        return null != this.reference;
    }

    @SuppressWarnings("unchecked")
    public <T> T get() {
        return Fn.getNull(() -> (T) this.reference, this.reference);
    }
}
