package io.vertx.tp.jet.uca.aim;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.jet.uca.JtAim;
import io.vertx.up.atom.Envelop;
import io.vertx.up.rs.hunt.Answer;

/*
 * Routing engine generation for Script Engine.
 */
public class EngineAim implements JtAim {
    private transient final JtMonitor monitor = JtMonitor.create(this.getClass());

    @Override
    public Handler<RoutingContext> attack(final JtUri uri) {
        return context -> {
            /*
             * Async log information in this step
             */
            final Envelop request = Answer.previous(context);
            final JsonObject data = request.data();
            this.monitor.aimEngine(uri.method(), uri.path(), data);
            /*
             * Next step
             */
            context.next();
        };
    }
}
