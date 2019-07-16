package io.vertx.up.plugin.redis;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.ext.web.sstore.impl.SharedDataSessionImpl;
import io.vertx.up.log.Annal;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class RedisStore implements SessionStore {

    private static final Annal LOGGER = Annal.get(RedisStore.class);

    /* Local Map */
    private LocalMap<String, Session> localMap;
    private List<String> sessionIds;

    /* Configuration Reference */
    private transient RedisExtra extra;
    private transient RedisAction session;
    private transient Vertx vertx;

    @Override
    public SessionStore init(final Vertx vertx, final JsonObject options) {
        /* RedisClient options here */
        this.vertx = vertx;
        session = new RedisAction(vertx, options);
        /* LocalMap */
        extra = options.mapTo(RedisExtra.class);
        localMap = vertx.sharedData().getLocalMap(RedisExtra.DEFAULT_SESSION_MAP_NAME);
        sessionIds = new Vector<>();

        return this;
    }

    @Override
    public long retryTimeout() {
        /* Get retry timeout field */
        return extra.getRetryTimeout();
    }

    @Override
    public Session createSession(final long timeout) {
        return new SharedDataSessionImpl(new PRNG(vertx), timeout, DEFAULT_SESSIONID_LENGTH);
    }

    @Override
    public Session createSession(final long timeout, final int length) {
        return new SharedDataSessionImpl(new PRNG(vertx), timeout, length);
    }

    @Override
    public void get(final String cookieValue, final Handler<AsyncResult<Session>> handler) {
        session.get(cookieValue, switcher -> combine(switcher, res -> {
            final Buffer buffer = res.result();
            if (Objects.nonNull(buffer)) {
                handler.handle(Future.succeededFuture(session.readSession(buffer)));
            } else {
                handler.handle(Future.succeededFuture(localMap.get(cookieValue)));
            }
        }));
    }

    @Override
    public void delete(final String id, final Handler<AsyncResult<Void>> handler) {
        session.remove(id, switcher -> combine(switcher, res -> {
            sessionIds.remove(id);
            LOGGER.info(RedisMsg.RD_CLEAR, res.result());
            /* Callback */
            handler.handle(Future.succeededFuture());
        }));
    }

    @Override
    public void put(final Session session, final Handler<AsyncResult<Void>> handler) {
        this.session.get(session.id(), switcher -> combine(switcher, res -> {
            final Buffer buffer = res.result();
            if (Objects.isNull(buffer)) {
                /* Data Missing */
                final RedisSession newSession = (RedisSession) session;
                newSession.incrementVersion();
                writeSession(session, handler);
            } else {
                /* Existing */
                final RedisSession oldSession = this.session.readSession(buffer);
                final RedisSession newSession = (RedisSession) session;
                if (oldSession.version() != newSession.version()) {
                    handler.handle(Future.failedFuture("Version Mismatch"));
                } else {
                    newSession.incrementVersion();
                    writeSession(session, handler);
                }
            }
        }));
    }

    private void writeSession(final Session session, final Handler<AsyncResult<Void>> handler) {
        this.session.writeSession(session, switcher -> {
            if (switcher.succeeded()) {
                LOGGER.info(RedisMsg.RD_KEYS, session.data());
                sessionIds.add(session.id());
                handler.handle(Future.succeededFuture());
            } else {
                handler.handle(Future.failedFuture(switcher.cause()));
            }
        });
    }

    @Override
    public void clear(final Handler<AsyncResult<Void>> handler) {
        sessionIds.forEach(sessionId -> session.remove(sessionId, switcher -> combine(switcher, res -> {
            /*
             * If this sessionId existing into List, but
             * redis it not expired
             */
            sessionIds.remove(sessionId);
            LOGGER.info(RedisMsg.RD_CLEAR, res.result());
        })));
        handler.handle(Future.succeededFuture());
    }

    @Override
    public void size(final Handler<AsyncResult<Integer>> handler) {
        handler.handle(Future.succeededFuture(sessionIds.size()));
    }

    @Override
    public void close() {
        session.close(switcher ->
                combine(switcher, res -> LOGGER.info(RedisMsg.RD_CLIENT)));
    }

    private <T> void combine(final AsyncResult<T> res, final Handler<AsyncResult<T>> handler) {
        if (res.succeeded()) {
            handler.handle(res);
        } else {
            handler.handle(Future.failedFuture(res.cause()));
        }
    }
}
