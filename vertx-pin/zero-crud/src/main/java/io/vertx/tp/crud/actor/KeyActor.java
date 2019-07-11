package io.vertx.tp.crud.actor;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.up.unity.Ux;
import io.vertx.up.commune.Envelop;
import io.vertx.up.util.Ut;

/*
 * key for primary key generation here
 * {
 *      "key": ""
 * }
 */
class KeyActor extends AbstractActor {

    @Override
    public JsonObject proc(final JsonObject data, final IxModule config) {
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
