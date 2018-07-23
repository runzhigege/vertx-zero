package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.atom.hold.Virtual;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.rs.Aim;

public class AsyncAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.getNull(() -> (context) -> Responser.exec(() -> {
            // 1. Build Envelop
            final Envelop request = this.invoke(context, event);
            // 2. Build event bus
            final Vertx vertx = context.vertx();
            final EventBus bus = vertx.eventBus();
            // 3. Send message
            final String address = this.address(event);
            bus.<Envelop>send(address, request, handler -> {
                final Envelop response;
                if (handler.succeeded()) {
                    // Request - Response message
                    response = this.success(address, handler);
                } else {
                    response = this.failure(address, handler);
                }
                Answer.reply(context, response, event);
            });
        }, context, event), event);
    }

    private Envelop invoke(final RoutingContext context,
                           final Event event) {
        final Object proxy = event.getProxy();
        // 1. Build Arguments
        final Object[] arguments = this.buildArgs(context, event);
        // Interface direct
        final Envelop invoked;
        if (Virtual.is(proxy)) {
            final JsonObject message = new JsonObject();
            for (int idx = 0; idx < arguments.length; idx++) {
                message.put(String.valueOf(idx), arguments[idx]);
            }
            // 2.1. Direct send arguments
            invoked = Flower.continuous(context, message);
        } else {
            // 2.2. Method call
            final Object returnValue = this.invoke(event, arguments);
            invoked = Flower.continuous(context, returnValue);
        }
        // 3. Envelop injection for User/Headers
        invoked.setHeaders(context.request().headers());
        invoked.setUser(context.user());
        invoked.setSession(context.session());
        invoked.setContext(context.data());
        return invoked;
    }
}
