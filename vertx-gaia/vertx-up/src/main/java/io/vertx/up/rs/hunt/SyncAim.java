package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.rs.Aim;

/**
 * SyncAim: Non-Event Bus: Request-Response
 */
public class SyncAim extends BaseAim implements Aim<RoutingContext> {
    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.getNull(() -> (context) -> Responser.exec(() -> {
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
