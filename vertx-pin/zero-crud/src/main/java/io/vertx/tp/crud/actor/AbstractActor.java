package io.vertx.tp.crud.actor;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;

import java.util.Objects;

public abstract class AbstractActor implements IxActor {

    private final transient JsonObject metadata = new JsonObject();
    private transient Envelop envelop;
    private transient String user;      // Stored current user information

    @Override
    public IxActor bind(final Envelop envelop) {
        this.envelop = envelop;
        final User user = envelop.user();
        if (Objects.nonNull(user)) {
            /* User information here */
            this.initLogged(user);
        }
        return this;
    }

    private void initLogged(final User user) {
        final JsonObject principle = user.principal();
        /* Metadata processing */
        if (principle.containsKey(KeField.METADATA)) {
            this.metadata.mergeIn(principle.getJsonObject(KeField.METADATA));
        }
        /* User extract */
        final String token = principle.getString("jwt");
        final JsonObject credential = Ux.Jwt.extract(token);
        /* User id */
        this.user = credential.getString("user");
    }

    protected Envelop getRequest() {
        return this.envelop;
    }

    protected String getUser() {
        return this.user;
    }

    protected JsonObject getMetadata() {
        return this.metadata;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
