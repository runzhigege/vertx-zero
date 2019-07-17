package io.vertx.up.uca.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.commune.Envelop;
import io.vertx.up.fn.Fn;
import io.vertx.up.uca.container.Virtual;
import io.vertx.up.uca.rs.Aim;

public class AsyncAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.getNull(() -> (context) -> exec(() -> {
            // 1. Build Envelop
            final Envelop request = invoke(context, event);
            // 2. Build event bus
            final Vertx vertx = context.vertx();
            final EventBus bus = vertx.eventBus();
            // 3. Send message
            final String address = address(event);

            bus.<Envelop>send(address, request, handler -> {
                final Envelop response;
                if (handler.succeeded()) {
                    // Request - Response message
                    response = success(address, handler);
                } else {
                    response = failure(address, handler);
                }
                Answer.reply(context, response, event);
            });
        }, context, event), event);
    }

    private Envelop invoke(final RoutingContext context,
                           final Event event) {
        final Object proxy = event.getProxy();
        // 1. Build Arguments
        final Object[] arguments = buildArgs(context, event);
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
            final Object returnValue = invoke(event, arguments);
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
