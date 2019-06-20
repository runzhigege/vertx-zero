package io.vertx.tp.ke.extension.view;

import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;

/*
 * Abstract Column
 */
public abstract class AbstractColumnMy implements ColumnMyStub {

    private transient UxJooq jooq;

    @Override
    public ColumnMyStub on(final UxJooq jooq) {
        this.jooq = jooq;
        return this;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
