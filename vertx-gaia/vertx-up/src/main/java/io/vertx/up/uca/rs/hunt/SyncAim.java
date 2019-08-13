package io.vertx.up.uca.rs.hunt;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.commune.Envelop;
import io.vertx.up.fn.Fn;
import io.vertx.up.uca.rs.Aim;

/**
 * SyncAim: Non-Event Bus: Request-Response
 */
public class SyncAim extends BaseAim implements Aim<RoutingContext> {
    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.getNull(() -> (context) -> exec(() -> {
            /*
             * Build arguments
             */
            final Object[] arguments = buildArgs(context, event);
            /*
             * Method callxx
             * Java reflector to call defined method.
             */
            final Object result = invoke(event, arguments);

            // 3. Resource model building
            // final Envelop data = Flower.continuous(context, result);
            /*
             * Data handler to process Flower next result here.
             */
            final Future<Envelop> future = Flower.next(context, result);
            future.setHandler(dataRes ->
                    /*
                     * Reply future result directly here.
                     */
                    Answer.reply(context, dataRes.result(), event));

        }, context, event), event);
    }
}
