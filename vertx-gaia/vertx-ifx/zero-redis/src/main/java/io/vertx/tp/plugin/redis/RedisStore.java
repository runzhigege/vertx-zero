package io.vertx.tp.plugin.redis;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import io.vertx.redis.op.SetOptions;
import io.vertx.tp.error._409SessionVersionException;
import io.vertx.up.exception.WebException;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.util.*;

public class RedisStore implements SessionStore {

    private static final Annal LOGGER = Annal.get(RedisStore.class);
    /* List Session ids */
    private final transient List<String> sessionIds = new ArrayList<>();
    /* Configuration Reference */
    private transient Vertx vertx;
    /* Client reference */
    private transient RedisClient client;
    /* Configuration of additional */
    private transient RedisExtra extra;

    @Override
    public SessionStore init(final Vertx vertx, final JsonObject options) {
        /* RedisClient options here */
        this.vertx = vertx;
        /* Redis options */
        final RedisOptions opts = new RedisOptions(options);
        LOGGER.info(RedisMsg.RD_OPTS,
                /* Endpoint information for current */
                opts.getHost() + ":" + String.valueOf(opts.getPort()),
                options.encode());
        /* Client init */
        client = RedisClient.create(vertx, options);

        /* Extra configuration options */
        extra = Ut.deserialize(options, RedisExtra.class);
        return this;
    }

    @Override
    public long retryTimeout() {
        /* Get retry timeout field */
        return extra.getRetryTimeout();
    }

    @Override
    public Session createSession(final long timeout) {
        return create(timeout, DEFAULT_SESSIONID_LENGTH);
    }

    @Override
    public Session createSession(final long timeout, final int length) {
        return create(timeout, length);
    }

    @Override
    public void clear(final Handler<AsyncResult<Void>> handler) {
        /*
         * To avoid: java.util.ConcurrentModificationException
         */
        final Set<String> idSet = new HashSet<>(sessionIds);
        idSet.forEach(sessionId -> client.del(sessionId, res -> {
            if (res.succeeded()) {
                /*
                 * This sessionId should be removed
                 */
                sessionIds.remove(sessionId);
            }
        }));
        handler.handle(Future.succeededFuture());
    }

    @Override
    public void size(final Handler<AsyncResult<Integer>> handler) {
        handler.handle(Future.succeededFuture(sessionIds.size()));
    }

    @Override
    public void close() {
        client.close(handler -> {
            if (handler.succeeded()) {
                /*
                 * When you close this client, the sessionIds should be cleared
                 */
                sessionIds.clear();

                LOGGER.info(RedisMsg.RD_CLOSE);
            }
        });
    }

    @Override
    public void get(final String id, final Handler<AsyncResult<Session>> handler) {
        LOGGER.info(RedisMsg.RS_MESSAGE, id, "get");
        readSession(id, res -> {
            final Buffer buffer = res.result();
            if (Objects.isNull(buffer)) {
                /*
                 * Null data get
                 * Create new session here, but be careful, in this method
                 * System do not put session into Redis, it's created new
                 * Session directly and returned to handler
                 */
                final Session session = readBuffer();
                handler.handle(Future.succeededFuture(session));
            } else {
                /*
                 * Data get from buffer
                 */
                final Session session = readBuffer(buffer);
                handler.handle(Future.succeededFuture(session));
            }
        });
    }

    @Override
    public void put(final Session session, final Handler<AsyncResult<Void>> handler) {
        LOGGER.info(RedisMsg.RS_MESSAGE, session.id(), "put");
        /* Before put */
        readSession(session.id(), res -> {
            if (res.succeeded()) {
                /* Whether get old session or new */
                final Buffer buffer = res.result();
                if (Objects.nonNull(buffer)) {
                    /* Build old session */
                    final RedisSession oldSession = (RedisSession) readBuffer(buffer);
                    final RedisSession newSession = (RedisSession) session;

                    if (oldSession.version() == newSession.version()) {
                        /*
                         * Version match, common workflow
                         */
                        newSession.incrementVersion();
                        writeSession(newSession, handler);
                    } else {
                        /*
                         * Version mismatch
                         */
                        final WebException error = new _409SessionVersionException(getClass(),
                                oldSession.version(), newSession.version());
                        handler.handle(Future.failedFuture(error));
                    }
                } else {
                    /*
                     * Data does not existing
                     */
                    final RedisSession newSession = (RedisSession) session;
                    newSession.incrementVersion();
                    writeSession(newSession, handler);
                }
            } else {
                /*
                 * ERROR throw out
                 */
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void delete(final String id, final Handler<AsyncResult<Void>> handler) {
        LOGGER.info(RedisMsg.RS_MESSAGE, id, "delete");
        client.del(id, res -> {
            if (res.succeeded()) {
                /*
                 * Synced sessionIds
                 */
                sessionIds.remove(id);
                handler.handle(Future.succeededFuture());
            } else {
                /*
                 * ERROR throw out
                 */
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    private void readSession(final String id, final Handler<AsyncResult<Buffer>> handler) {
        client.getBinary(id, res -> {
            if (res.succeeded()) {
                final Buffer buffer = res.result();
                handler.handle(Future.succeededFuture(buffer));
            } else {
                /*
                 * ERROR throw out
                 */
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    private void writeSession(final Session session, final Handler<AsyncResult<Void>> handler) {
        /* Write buffer */
        final Buffer buffer = writeBuffer(session);

        /* Synced timeout here */
        final SetOptions options = new SetOptions().setPX(session.timeout());
        /* Data writing here */
        final String key = session.id();
        client.setBinaryWithOptions(key, buffer, options, res -> {
            if (res.succeeded()) {
                LOGGER.info(RedisMsg.RD_KEYS, Ut.fromJoin(session.data().keySet()));
                /*
                 * Add data if this id does not exist.
                 */
                if (!sessionIds.contains(key)) {
                    sessionIds.add(key);
                }
                handler.handle(Future.succeededFuture());
            } else {
                /*
                 * ERROR throw out
                 */
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    private Session readBuffer() {
        return readBuffer(Buffer.buffer());
    }

    private Session readBuffer(final Buffer buffer) {
        final Session session = createSession(extra.getTimeout());
        /* Read data from buffer */
        ((RedisSession) session).readFromBuffer(0, buffer);
        return session;
    }

    private Buffer writeBuffer(final Session session) {
        /* Filling data with Buffer */
        final Buffer buffer = Buffer.buffer();
        final RedisSession sessionImpl = (RedisSession) session;
        /* Session serialized into buffer */
        sessionImpl.writeToBuffer(buffer);
        return buffer;
    }

    private RedisSession create(final long timeout, final int length) {
        return new RedisSession(new PRNG(vertx), timeout, length);
    }
}
