package io.vertx.tp.ke.extension.view;

import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

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

    protected String getUser(final JsonObject principle) {
        final String token = principle.getString("jwt");
        if (Ut.notNil(token)) {
            final JsonObject data = Ux.Jwt.extract(token);
            if (!Ut.isNil(data)) {
                return data.getString("user");
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
