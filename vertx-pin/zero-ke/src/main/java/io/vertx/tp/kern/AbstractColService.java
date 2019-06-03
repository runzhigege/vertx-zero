package io.vertx.tp.kern;

import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;

/*
 * Abstract Column
 */
public abstract class AbstractColService implements ColStub {

    private transient UxJooq jooq;

    @Override
    public ColStub on(final UxJooq jooq) {
        this.jooq = jooq;
        return this;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
