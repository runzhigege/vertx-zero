package io.vertx.tp.jet.uca.param;

import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.up.atom.Envelop;
import io.zero.epic.fn.Fn;

/*
 * The unique interface for handler process here.
 */
public interface JtIngest {
    static JtIngest getInstance() {
        return Fn.poolThread(Pool.POOL_INGEST, DataIngest::new);
    }

    /*
     * Different workflow will call component other
     */
    Envelop in(RoutingContext context, JtUri uri);
}
