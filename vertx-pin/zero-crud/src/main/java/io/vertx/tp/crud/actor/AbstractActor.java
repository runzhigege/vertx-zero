package io.vertx.tp.crud.actor;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;

import java.util.Objects;

public abstract class AbstractActor implements IxActor {

    private transient Envelop envelop;

    @Override
    public IxActor bind(final Envelop envelop) {
        this.envelop = envelop;
        return this;
    }

    protected Envelop getRequest() {
        return this.envelop;
    }

    private JsonObject getPrincipal() {
        final User user = this.envelop.user();
        final JsonObject result = new JsonObject();
        if (null != user) {
            final JsonObject principal = user.principal();
            if (Objects.nonNull(principal)) {
                result.mergeIn(principal);
            }
        }
        return result;
    }

    protected String getUser() {
        final JsonObject principal = this.getPrincipal();
        return principal.getString("user");
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
