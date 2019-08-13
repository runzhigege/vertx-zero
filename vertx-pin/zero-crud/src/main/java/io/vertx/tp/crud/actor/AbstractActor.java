package io.vertx.tp.crud.actor;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.commune.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;

import java.util.Objects;

public abstract class AbstractActor implements IxActor {

    private final transient JsonObject metadata = new JsonObject();
    private transient Envelop envelop;
    private transient String user;      // Stored current user information
    private transient String habitus;   // Get session here.

    @Override
    public IxActor bind(final Envelop envelop) {
        this.envelop = envelop;
        final User user = envelop.user();
        if (Objects.nonNull(user)) {
            /* User information here */
            initLogged(user);
        }
        return this;
    }

    private void initLogged(final User user) {
        final JsonObject principle = user.principal();
        /* Metadata processing */
        if (principle.containsKey(KeField.METADATA)) {
            metadata.mergeIn(principle.getJsonObject(KeField.METADATA));
        }
        /* User extract */
        final String token = principle.getString("jwt");
        final JsonObject credential = Ux.Jwt.extract(token);
        /* User id */
        this.user = credential.getString("user");
        /* Session */
        habitus = Ke.keyHabitus(envelop);
    }

    protected Envelop getRequest() {
        return envelop;
    }

    protected String getUser() {
        return user;
    }

    protected String getHabitus() {
        return habitus;
    }

    protected JsonObject getMetadata() {
        return metadata;
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }
}
