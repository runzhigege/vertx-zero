package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Aim;
import io.vertx.up.tool.mirror.Types;

/**
 * BlockAim: Non-Event Bus: One-Way
 */
public class PingAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.get(() -> (context) -> Responser.exec(() -> {
            // 1. Build Arguments
            final Object[] arguments = buildArgs(context, event);

            // 2. Method call
            final Object invoked = invoke(event, arguments);
            // 3. Resource model building
            final Envelop data;
            if (Types.isBoolean(invoked)) {
                data = Envelop.success((Boolean) invoked);
            } else {
                data = Envelop.success(Boolean.TRUE);
            }
            // 4. Process modal
            Answer.reply(context, data, event);
        }, context, event), event);
    }
}
