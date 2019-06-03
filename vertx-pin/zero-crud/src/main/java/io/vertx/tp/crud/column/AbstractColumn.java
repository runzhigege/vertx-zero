package io.vertx.tp.crud.column;

import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;

/*
 * Abstract Column
 */
public abstract class AbstractColumn implements ColStub {

    private transient UxJooq jooq;
    private transient IxConfig config;

    @Override
    public ColStub on(final UxJooq jooq) {
        this.jooq = jooq;
        return this;
    }

    @Override
    public ColStub on(final IxConfig config) {
        this.config = config;
        return this;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
