package io.vertx.tp.jet.uca.param;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.optic.jet.JtIngest;
import io.vertx.up.atom.Envelop;

import java.util.Map;

/*
 * package scope,
 * /api/xxx/:name
 *
 * Only parsed uri and get uri pattern
 * -->
 *    name = xxxx
 */
class PathIngest implements JtIngest {

    @Override
    public Envelop in(final RoutingContext context, final JtUri uri) {
        /*
         * Pattern extract only
         */
        final Map<String, String> params = context.pathParams();
        final JsonObject data = new JsonObject();
        params.forEach(data::put);
        return Envelop.success(data);
    }
}
