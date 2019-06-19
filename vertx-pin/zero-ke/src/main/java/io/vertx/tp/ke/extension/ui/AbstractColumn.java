package io.vertx.tp.ke.extension.ui;

import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;

/*
 * Abstract Column
 */
public abstract class AbstractColumn implements ColumnStub {

    private transient UxJooq jooq;

    @Override
    public ColumnStub on(final UxJooq jooq) {
        this.jooq = jooq;
        return this;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
