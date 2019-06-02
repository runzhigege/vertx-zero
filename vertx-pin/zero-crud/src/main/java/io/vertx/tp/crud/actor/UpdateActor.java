package io.vertx.tp.crud.actor;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.tp.crud.refine.Ix;
import io.zero.epic.Ut;

class UpdateActor extends AbstractActor {

    @Override
    public JsonObject proc(final JsonObject data, final IxConfig config) {
        /* UserId */
        final String userId = this.getUser();
        if (Ut.notNil(userId)) {
            final IxField field = config.getField();
            /* Created */
            Ix.audit(data, field.getUpdated(), userId);
        }
        return data;
    }
}
