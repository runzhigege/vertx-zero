package io.vertx.tp.jet.uca.aim;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.jet.uca.JtAim;
import io.vertx.up.atom.Envelop;
import io.vertx.up.rs.hunt.Answer;

public class SendAim implements JtAim {

    private transient final JtMonitor monitor = JtMonitor.create(this.getClass());

    @Override
    public Handler<RoutingContext> attack(final JtUri uri) {
        /*
         * 「Booting Life Cycle」
         *  Code Area
         */
        return context -> {
            /*
             * 「Request Life Cycle」
             */
            final Envelop request = Answer.previous(context);
            /*
             * Set id here, consumer will extract api data in worker
             */
            request.key(uri.key());

            final JsonObject data = request.data();
            final String address = uri.worker().getWorkerAddress(); // Address
            /*
             * Monitor data and address
             */
            this.monitor.aimSend(data, address);

            final Vertx vertx = context.vertx();
            final EventBus event = vertx.eventBus();

            event.<Envelop>send(address, request, handler -> {
                if (handler.succeeded()) {
                    /*
                     * 「Success」
                     */
                    final Message<Envelop> result = handler.result();
                    Answer.reply(context, result.body(), uri::producesMime);
                } else {
                    /*
                     * 「Failure」
                     */
                    final Envelop error = Envelop.failure(handler.cause());
                    Answer.reply(context, error);
                }
            });
        };
    }
}
