package io.vertx.tp.crud.actor;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;

class KeyActor extends AbstractActor {

    @Override
    public JsonObject proc(final JsonObject data, final IxConfig config) {
        final Envelop request = this.getRequest();
        final IxField field = config.getField();
        /* Primary Key Add */
        if (Ut.notNil(field.getKey())) {
            final String keyValue = Ux.getString1(request);
            if (Ut.notNil(keyValue)) {
                data.put(field.getKey(), keyValue);
            }
        }
        return data;
    }
}
