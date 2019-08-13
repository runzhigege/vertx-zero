package io.vertx.up.uca.rs.hunt;

import io.vertx.core.Future;
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
import io.vertx.up.unity.Ux;

public class AsyncAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.getNull(() -> (context) -> exec(() -> {
            /*
             * Build future ( data handler )
             */
            final Future<Envelop> future = invoke(context, event);

            /*
             * Event bus building / get from vertx instance.
             */
            final Vertx vertx = context.vertx();
            final EventBus bus = vertx.eventBus();
            final String address = address(event);

            /*
             * New method instead of old
             * -- request(address, T, handler)
             */
            future.setHandler(dataRes -> bus.<Envelop>request(address, dataRes.result(), handler -> {
                final Envelop response;
                if (handler.succeeded()) {
                    // Request - Response message
                    response = success(address, handler);
                } else {
                    response = failure(address, handler);
                }
                Answer.reply(context, response, event);
            }));
            /*
            bus.<Envelop>send(address, request, handler -> {
                final Envelop response;
                if (handler.succeeded()) {
                    // Request - Response message
                    response = success(address, handler);
                } else {
                    response = failure(address, handler);
                }
                Answer.reply(context, response, event);
            });*/
        }, context, event), event);
    }

    private Future<Envelop> invoke(final RoutingContext context,
                                   final Event event) {
        final Object proxy = event.getProxy();
        /*
         * Method arguments building here.
         */
        final Object[] arguments = buildArgs(context, event);
        /*
         * Whether it's interface mode or agent mode
         */
        final Future<Envelop> invoked;
        if (Virtual.is(proxy)) {
            final JsonObject message = new JsonObject();
            for (int idx = 0; idx < arguments.length; idx++) {
                message.put(String.valueOf(idx), arguments[idx]);
            }
            /*
             * Interface mode
             */
            invoked = Flower.next(context, message);
        } else {
            /*
             * Agent mode
             */
            final Object returnValue = invoke(event, arguments);
            invoked = Flower.next(context, returnValue);
        }

        return invoked.compose(response -> {
            /*
             * The next method of compose for future building assist data such as
             * Headers,
             * User,
             * Session
             * Context
             * It's critical for Envelop object when communication
             */
            response.setHeaders(context.request().headers());
            response.setUser(context.user());
            response.setSession(context.session());
            response.setContext(context.data());
            return Ux.toFuture(response);
        });
    }
}
