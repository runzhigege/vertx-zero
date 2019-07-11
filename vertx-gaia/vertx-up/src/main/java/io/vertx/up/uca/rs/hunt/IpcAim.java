package io.vertx.up.uca.rs.hunt;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.commune.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.uca.micro.ipc.client.TunnelClient;
import io.vertx.up.uca.rs.Aim;
import io.vertx.up.fn.Fn;

public class IpcAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.getNull(() -> (context) -> this.exec(() -> {
            // 1. Build Arguments
            final Object[] arguments = this.buildArgs(context, event);

            // 2. Method call
            final Object result = this.invoke(event, arguments);

            // 3. Resource model building
            final Envelop data = Flower.continuous(context, result);

            // 4. Rpc Client Call to send the data.
            final Future<Envelop> handler = TunnelClient.create(this.getClass())
                    .connect(context.vertx())
                    .connect(event.getAction())
                    .send(data);
            // 5. Reply
            handler.setHandler(res -> Answer.reply(context, res.result()));
        }, context, event), event);
    }
}
