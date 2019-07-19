package io.vertx.up.uca.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.commune.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.uca.rs.Aim;
import io.vertx.up.fn.Fn;

/**
 * SyncAim: Non-Event Bus: Request-Response
 */
public class SyncAim extends BaseAim implements Aim<RoutingContext> {
    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.getNull(() -> (context) -> this.exec(() -> {
            // 1. Build Arguments
            final Object[] arguments = this.buildArgs(context, event);
            // 2. Method call
            final Object result = this.invoke(event, arguments);

            // 3. Resource model building
            final Envelop data = Flower.continuous(context, result);
            // 4. Process modal
            Answer.reply(context, data, event);

        }, context, event), event);
    }
}