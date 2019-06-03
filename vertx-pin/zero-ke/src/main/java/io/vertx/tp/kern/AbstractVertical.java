package io.vertx.tp.kern;

import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;

/*
 * Abstract Column
 */
public abstract class AbstractVertical implements VerticalStub {

    private transient UxJooq jooq;

    @Override
    public VerticalStub on(final UxJooq jooq) {
        this.jooq = jooq;
        return this;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
