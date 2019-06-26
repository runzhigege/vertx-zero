package io.vertx.tp.optic.fantom;

import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;

/*
 * All sub-class for extension of Jooq type
 */
public abstract class Anchoret<T> {

    private transient UxJooq jooq;

    /*
     * This method is for sub-class only
     */
    @SuppressWarnings("unchecked")
    public T on(final UxJooq jooq) {
        this.jooq = jooq;
        return (T) this;
    }

    protected UxJooq dao() {
        return this.jooq;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
