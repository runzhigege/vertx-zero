package io.vertx.tp.crud.actor;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;

class HeaderActor extends AbstractActor {

    @Override
    public JsonObject proc(final JsonObject data, final IxConfig config) {
        final Envelop request = this.getRequest();
        final MultiMap headers = request.headers();
        /* Header Data */
        final JsonObject headerConfig = config.getHeader();
        if (null != headerConfig) {
            Ut.itJObject(headerConfig, (to, from) -> {
                final String value = headers.get(to.toString());
                if (Ut.notNil(value)) {
                    data.put(from, value);
                }
            });
        }
        return data;
    }
}
