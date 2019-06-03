package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.error._404ModuleMissingException;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.log.Annal;

import java.util.Locale;
import java.util.function.BiFunction;

public class IxAtom {
    private final transient Class<?> target;
    private transient IxConfig config;
    private transient UxJooq jooq;
    private transient WebException ex;

    private IxAtom(final Class<?> clazz) {
        this.target = clazz;
    }

    static IxAtom create(final Class<?> clazz) {
        return new IxAtom(clazz);
    }

    private void initDao(final String actor) {
        try {
            this.config = IxPin.getActor(actor);
            this.jooq = IxPin.getDao(this.config);
            if (null == this.jooq) {
                this.ex = new _404ModuleMissingException(this.target, actor);
            }
        } catch (final WebException error) {
            this.ex = error;
        }
    }

    private void logRequest(final Envelop envelop) {
        final HttpMethod method = envelop.getMethod();
        final String uri = envelop.getUri();
        final Annal logger = Annal.get(this.target);
        logger.info("[ Εκδήλωση ] ---> Uri Addr : {0} {1}",
                method.name().toUpperCase(Locale.getDefault()), uri);
    }

    public IxAtom input(final Envelop envelop) {
        final String actor = Ux.getString(envelop);
        /* */
        this.initDao(actor);

        this.logRequest(envelop);
        return this;
    }

    public Future<Envelop> envelop(final BiFunction<UxJooq, IxConfig, Future<Envelop>> actuator) {
        final WebException error = this.ex;
        if (null == error) {
            try {
                return actuator.apply(this.jooq, this.config);
            } catch (final WebException ex) {
                return Future.failedFuture(ex);
            } catch (final Throwable ex) {
                ex.printStackTrace();
                return Future.failedFuture(ex);
            }
        } else {
            /* IxIn Error */
            return Future.failedFuture(error);
        }
    }
}
