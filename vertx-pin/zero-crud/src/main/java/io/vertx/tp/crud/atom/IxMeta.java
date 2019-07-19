package io.vertx.tp.crud.atom;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.error._404ModuleMissingException;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxJooq;
import io.vertx.up.commune.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.log.Annal;

import java.util.function.BiFunction;

public class IxMeta {
    private final transient Class<?> target;
    private transient IxModule config;
    private transient UxJooq jooq;
    private transient WebException ex;

    private IxMeta(final Class<?> clazz) {
        this.target = clazz;
    }

    public static IxMeta create(final Class<?> clazz) {
        return new IxMeta(clazz);
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

        Ix.infoRest(logger, "---> Uri Addr: {0} {1}", method, uri);
    }

    public IxMeta input(final Envelop envelop) {
        final String actor = Ux.getString(envelop);
        /* */
        this.initDao(actor);

        this.logRequest(envelop);
        return this;
    }

    public Future<Envelop> envelop(final BiFunction<UxJooq, IxModule, Future<Envelop>> actuator) {
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