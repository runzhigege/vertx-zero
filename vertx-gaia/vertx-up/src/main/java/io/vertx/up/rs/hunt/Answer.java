package io.vertx.up.rs.hunt;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.rs.pointer.PluginExtension;

/**
 * Response process to normalize the response request.
 * 1. Media definition
 * 2. Operation based on event, envelop, context
 */
public final class Answer {

    public static void reply(
            final RoutingContext context,
            final Envelop envelop) {
        // 1. Get response reference
        final HttpServerResponse response =
                Normalizer.initialize(context, envelop);
        // FIX: java.lang.IllegalStateException: Response is closed
        if (!response.closed()) {
            /* Bind Data */
            envelop.bind(context);

            // 2. Media processing
            Normalizer.media(response, null);

            /* Plugin Extension for response replying */
            PluginExtension.Answer.reply(context, envelop);

            // 3. Response process
            Normalizer.out(response, envelop, null);
        }
    }

    public static void reply(
            final RoutingContext context,
            final Envelop envelop,
            final Event event
    ) {
        // 1. Get response reference
        final HttpServerResponse response =
                Normalizer.initialize(context, envelop);
        // FIX: java.lang.IllegalStateException: Response is closed
        if (!response.closed()) {
            /* Bind Data */
            envelop.bind(context);

            // 2. Media processing
            Normalizer.media(response, event);

            // 3. Store Session
            Normalizer.storeSession(context, envelop.data(), event.getAction());

            /* Plugin Extension for response replying */
            PluginExtension.Answer.reply(context, envelop);

            // 3. Response process
            Normalizer.out(response, envelop, event);
        }
    }
}
