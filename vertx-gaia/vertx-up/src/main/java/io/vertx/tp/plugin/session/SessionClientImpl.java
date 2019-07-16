package io.vertx.tp.plugin.session;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.up.eon.em.StoreType;
import io.vertx.up.exception.web._500SessionClientInitException;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.util.concurrent.atomic.AtomicBoolean;

public class SessionClientImpl implements SessionClient {

    private static final Annal LOGGER = Annal.get(SessionClientImpl.class);
    private static final AtomicBoolean LOG_MSG = new AtomicBoolean(true);
    private static SessionStore STORE;
    private final transient Vertx vertx;

    private SessionClientImpl(final Vertx vertx, final JsonObject config, final StoreType type) {
        this.vertx = vertx;
        if (null == STORE) {
            if (LOG_MSG.getAndSet(Boolean.FALSE)) {
                LOGGER.info(Info.SESSION_MODE, type);
            }
            /* Whether existing store */
            if (StoreType.LOCAL == type) {
                STORE = LocalSessionStore.create(this.vertx);
            } else if (StoreType.CLUSTER == type) {
                STORE = ClusteredSessionStore.create(this.vertx);
            } else {
                final String store = config.getString("store");
                Fn.outWeb(Ut.isNil(store), _500SessionClientInitException.class, getClass());
                LOGGER.info(Info.SESSION_STORE, store);
                /*
                 * SessionStore -> Defined here
                 */
                final SessionStore defined = Ut.singleton(store);
                JsonObject opts = config.getJsonObject("options");
                if (Ut.isNil(opts)) {
                    opts = new JsonObject();
                }
                STORE = defined.init(vertx, opts);
            }
        }
    }

    static SessionClientImpl create(final Vertx vertx, final JsonObject config) {
        final String type = config.getString("category");
        if ("CLUSTER".equals(type)) {
            /* CLUSTER */
            return new SessionClientImpl(vertx, config, StoreType.CLUSTER);
        } else if ("DEFINED".equals(type)) {
            /* DEFINED */
            return new SessionClientImpl(vertx, config, StoreType.DEFINED);
        } else {
            /* LOCAL ( Default ) */
            return new SessionClientImpl(vertx, config, StoreType.LOCAL);
        }
    }

    @Override
    public SessionHandler getHandler() {
        return SessionHandler.create(STORE);
    }

    @Override
    public Future<Session> get(final String id) {
        final Future<Session> future = Future.future();
        STORE.get(id, result -> {
            if (result.succeeded()) {
                future.complete(result.result());
            } else {
                future.complete(null);
            }
        });
        return future;
    }
}
