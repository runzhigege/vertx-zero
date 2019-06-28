package io.vertx.tp.jet.uca.param;

import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.jet.cv.em.ParamMode;
import io.vertx.tp.optic.jet.JtIngest;
import io.vertx.up.atom.Envelop;

import java.util.Map;
import java.util.function.Supplier;

/*
 * package scope,
 * /api/xxx/:name
 *
 * Only parsed uri and get uri pattern
 * -->
 *    name = xxxx
 */
class PathIngest implements JtIngest {

    private transient final Supplier<JtIngest> supplier = Pool.INNER_INGEST.get(ParamMode.HEADER);

    @Override
    public Envelop in(final RoutingContext context, final JtUri uri) {
        final JtIngest ingest = this.supplier.get();
        final Envelop envelop = ingest.in(context, uri);
        /*
         * Pattern extract only
         */
        final Map<String, String> params = context.pathParams();
        params.forEach(envelop::setValue);
        return envelop;
    }
}
