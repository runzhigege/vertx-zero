package io.vertx.up.web.anima;

import io.vertx.core.Vertx;
import io.vertx.up.aiki.Uobj;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.atom.worker.Receipt;
import io.vertx.zero.epic.Ut;
import io.vertx.zero.runtime.Runner;
import io.vertx.zero.runtime.ZeroAnno;

import java.util.Set;

/**
 * Injection system
 */
public class AffluxScatter implements Scatter<Vertx> {
    @Override
    public void connect(final Vertx vertx) {
        final Uobj injector = Uobj.create(this.getClass());
        // Extract all events.
        final Set<Event> events = ZeroAnno.getEvents();
        Ut.itSet(events, (item, index) ->
                Runner.run(() -> injector.singleton(item.getProxy())
                        , "event-afflux-" + index));

        // Extract all receipts.
        final Set<Receipt> receipts = ZeroAnno.getReceipts();
        Ut.itSet(receipts, (item, index) ->
                Runner.run(() -> injector.singleton(item.getProxy())
                        , "receipt-afflux-" + index));

        // Extract non - event/receipts Objects
        final Set<Class<?>> injects = ZeroAnno.getInjects();
        Ut.itSet(injects, (item, index) ->
                Runner.run(() -> injector.singleton(item),
                        "injects-afflux-" + index));
    }
}
