package io.vertx.up.rs.hunt;

import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Address;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Rule;
import io.vertx.up.atom.agent.Depot;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.eon.ID;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._500DeliveryErrorException;
import io.vertx.up.exception._500EntityCastException;
import io.vertx.up.log.Annal;
import io.vertx.up.media.Analyzer;
import io.vertx.up.media.MediaAnalyzer;
import io.vertx.up.rs.validation.Validator;
import io.vertx.up.epic.Ut;
import io.vertx.up.fn.Actuator;
import io.vertx.up.fn.Fn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Base class to provide template method
 */
public abstract class BaseAim {

    private transient final Validator verifier =
            Validator.create();

    private transient final Analyzer analyzer =
            Ut.singleton(MediaAnalyzer.class);

    /**
     * Template method
     *
     * @param context RoutingContext reference
     * @param event   Event object of definition
     * @return Arguments ( Object[] )
     */
    @SuppressWarnings("all")
    protected Object[] buildArgs(final RoutingContext context,
                                 final Event event) {
        Object[] cached = context.get(ID.PARAMS_CONTENT);
        if (null == cached) {
            cached = this.analyzer.in(context, event);
            context.put(ID.PARAMS_CONTENT, cached);
        }
        // Validation handler has been getNull the parameters.
        return cached;
    }


    /**
     * Get event bus address.
     *
     * @param event Event object of definition
     * @return Get event bus address
     */
    protected String address(final Event event) {
        final Method method = event.getAction();
        final Annotation annotation = method.getDeclaredAnnotation(Address.class);
        return Ut.invoke(annotation, "value");
    }

    /**
     * @param event Event object of definition
     * @param args  Arguments ( Object[] )
     * @return Return invoked result
     */
    protected Object invoke(final Event event, final Object[] args) {
        final Method method = event.getAction();
        this.getLogger().info("[ ZERO-DEBUG ] Method = {0}, Args = {1}",
                method.getName(), Ut.fromJoin(args));
        return Ut.invoke(event.getProxy(), method.getName(), args);
    }

    protected Envelop failure(final String address,
                              final AsyncResult<Message<Envelop>> handler) {
        final WebException error
                = new _500DeliveryErrorException(this.getClass(),
                address,
                Fn.getNull(null,
                        () -> handler.cause().getMessage(), handler.cause()));
        return Envelop.failure(error);
    }

    protected Envelop success(final String address,
                              final AsyncResult<Message<Envelop>> handler
    ) {
        Envelop envelop;
        try {
            final Message<Envelop> message = handler.result();
            envelop = message.body();
        } catch (final Throwable ex) {
            final WebException error
                    = new _500EntityCastException(this.getClass(),
                    address, ex.getMessage());
            envelop = Envelop.failure(error);
        }
        return envelop;
    }

    protected Validator verifier() {
        return this.verifier;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }

    protected void executeRequest(final RoutingContext context,
                                  final Map<String, List<Rule>> rulers,
                                  final Depot depot) {
        try {
            final Object[] args = this.buildArgs(context, depot.getEvent());
            // Execute web flow and uniform call.
            Flower.executeRequest(context, rulers, depot, args, this.verifier());
        } catch (final WebException error) {
            // Bad request of 400 for parameter processing
            Flower.replyError(context, error, depot.getEvent());
        } catch (final Exception ex) {
            // DEBUG:
            ex.printStackTrace();
        }
    }

    protected void exec(final Actuator consumer,
                        final RoutingContext context,
                        final Event event) {
        try {
            consumer.execute();
        } catch (final WebException ex) {
            final Envelop envelop = Envelop.failure(ex);
            Answer.reply(context, envelop, event);
        }
    }
}
