package io.vertx.tp.jet.uca.param;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.jet.cv.JtKey;
import io.vertx.up.atom.Envelop;

class HeaderIngest implements JtIngest {
    @Override
    public Envelop in(final RoutingContext context, final JtUri uri) {
        /* Header */
        final MultiMap headers = context.request().headers();
        final JsonObject headerData = new JsonObject();
        headers.names().stream()
                .filter(field -> field.startsWith("X"))
                .forEach(field -> headerData.put(field, headers.get(field)));
        return Envelop.success(new JsonObject().put(JtKey.PARAM_HEADER, headerData));
    }
}
