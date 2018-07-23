package io.vertx.up.web.anima;

import io.vertx.core.Vertx;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.atom.worker.Receipt;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.eon.Plugins;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.mirror.Anno;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroAnno;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Injection system
 */
public class AffluxScatter implements Scatter<Vertx> {

    private static final Annal LOGGER = Annal.get(AffluxScatter.class);

    private static final ConcurrentMap<Class<?>, ConcurrentMap<String, Class<?>>>
            PENDINGS = ZeroAnno.getPlugins();

    private static final AffluxInfix INJECTOR = AffluxInfix.create(AffluxScatter.class);

    @Override
    public void connect(final Vertx vertx) {
        // Extract all events.
        final Set<Event> events = ZeroAnno.getEvents();
        Ut.itSet(events, (item, index) ->
                Runner.run(() -> this.inject(item.getProxy())
                        , "event-afflux-" + index));

        // Extract all receipts.
        final Set<Receipt> receipts = ZeroAnno.getReceipts();
        Ut.itSet(receipts, (item, index) ->
                Runner.run(() -> this.inject(item.getProxy())
                        , "receipt-afflux-" + index));

        // Extract non - event/receipts Objects
        final Set<Class<?>> injects = ZeroAnno.getInjects();
        Ut.itSet(injects, (item, index) -> Runner.run(() -> {
            // Initialize object
            final Object instance = Instance.singleton(item);
            // Initialize reference
            this.inject(instance);
        }, "injects-afflux-" + index));
    }

    private void inject(final Object proxy, final String key, final Class<?> type) {
        // Field set
        try {
            final Class<?> clazz = proxy.getClass();
            final Field field = clazz.getDeclaredField(key);
            final Object instance;
            if (Anno.isMark(field, Plugins.INFIX_MAP.keySet())) {
                // Speicific Annotation
                instance = INJECTOR.inject(field);
            } else {
                // Inject Only
                instance = Instance.singleton(type);
            }
            // Set for field
            if (null != instance) {
                Instance.set(proxy, key, instance);
                // Scan continue for field
                this.inject(instance);
            }
        } catch (final NoSuchFieldException ex) {
            LOGGER.jvm(ex);
        }
    }

    private void inject(final Object proxy) {
        Fn.safeNull(() -> {
            final Class<?> clazz = proxy.getClass();
            if (PENDINGS.containsKey(clazz)) {
                // Scanned in started up
                final ConcurrentMap<String, Class<?>> injections
                        = PENDINGS.get(clazz);
                // Inject field
                injections.forEach((key, type) -> this.inject(proxy, key, type));
            }
        }, proxy);
    }
}
