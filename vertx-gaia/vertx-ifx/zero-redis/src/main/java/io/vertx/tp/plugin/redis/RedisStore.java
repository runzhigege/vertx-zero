package io.vertx.tp.plugin.redis;

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
    private static final String MAP_NAME = "vertx-web.sessions";
    private static transient PRNG random;
    /* Local Map */
    private final transient List<String> sessionIds = new Vector<>();
    /* Client reference */
    private transient RedisClient client;
    private transient LocalMap<String, Session> localMap;

    /* Configuration of additional */
    private transient RedisExtra extra;

    @Override
    public SessionStore init(final Vertx vertx, final JsonObject options) {
        /* RedisClient options here */
        random = new PRNG(vertx);
        localMap = vertx.sharedData().getLocalMap(MAP_NAME);

        /* Redis options */
        final RedisOptions opts = new RedisOptions(options);
        LOGGER.info(RedisMsg.RD_OPTS,
                /* Endpoint information for current */
                opts.getHost() + ":" + opts.getPort(),
                opts.toJson().encode());

        /* Client init, old version */
        client = new RedisClientStub(vertx, opts);

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
        return new RedisSession(random, timeout, DEFAULT_SESSIONID_LENGTH);
    }

    @Override
    public Session createSession(final long timeout, final int length) {
        return new RedisSession(random, timeout, length);
    }

    private RedisSession createSession() {
        return new RedisSession(random, extra.getTimeout(), DEFAULT_SESSIONID_LENGTH);
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
        /*
         * Session IDs
         */
        handler.handle(Future.succeededFuture(sessionIds.size()));
    }

    @Override
    public void close() {
        /*
         * Close Handler
         */
        client.close(handler -> {
            if (handler.succeeded()) {
                // sessionIds.clear();
                LOGGER.info(RedisMsg.RD_CLOSE);
            }
        });
    }

    @Override
    public void delete(final String id, final Handler<AsyncResult<Void>> handler) {
        LOGGER.debug(RedisMsg.RS_MESSAGE, id, "delete(String)");
        client.del(id, res -> {
            if (res.succeeded()) {
                /*
                 * Synced SessionIds
                 */
                sessionIds.remove(id);

                LOGGER.info(RedisMsg.RD_CLEAR, id);
                handler.handle(Future.succeededFuture());
            } else {
                /*
                 * ERROR throw out
                 */
                res.cause().printStackTrace();
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void get(final String id, final Handler<AsyncResult<Session>> handler) {
        LOGGER.debug(RedisMsg.RS_MESSAGE, id, "get(String)");
        client.getBinary(id, res -> {
            /*
             * Whether get buffer data after read data from redis.
             * If successful continue to process buffer data
             */
            if (res.succeeded()) {
                final Buffer buffer = res.result();
                if (Objects.nonNull(buffer)) {
                    final RedisSession session = createSession();
                    if (0 < buffer.length()) {
                        session.readFromBuffer(0, buffer);
                    }
                    handler.handle(Future.succeededFuture(session));
                } else {
                    /*
                     * LocalMap of vertx-web.sessions
                     */
                    handler.handle(Future.succeededFuture(localMap.get(id)));
                }
            } else {
                /*
                 * ERROR throw out
                 */
                res.cause().printStackTrace();
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    @SuppressWarnings("all")
    public void put(final Session session, final Handler<AsyncResult<Void>> handler) {
        final String id = session.id();
        LOGGER.debug(RedisMsg.RS_MESSAGE, id, "put(Session)");
        client.getBinary(id, res -> {
            if (res.succeeded()) {
                final Buffer buffer = res.result();
                final RedisSession finalSession;
                final RedisSession oldSession = createSession();
                if (Objects.isNull(buffer)) {
                    /*
                     * Data null
                     */
                    RedisSession sessionImpl = (RedisSession) session;
                    finalSession = sessionImpl;
                } else {
                    /*
                     * Data Existing
                     */
                    final RedisSession newSession = (RedisSession) session;
                    oldSession.readFromBuffer(0, buffer);
                    /*
                     * Version matching here for comparing
                     */
                    if (oldSession.version() != newSession.version()) {
                        final WebException error = new _409SessionVersionException(getClass(),
                                oldSession.version(), newSession.version());
                        handler.handle(Future.failedFuture(error));
                        return;
                    }
                    finalSession = newSession;
                }
                finalSession.incrementVersion();
                /*
                 * Write session data
                 */
                writeSession(session, res2 -> {
                    if (res2.succeeded()) {
                        if (Objects.nonNull(res2.result())) {
                            final String added = res2.result();
                            if (!sessionIds.contains(added)) {
                                sessionIds.add(res2.result());
                            }
                            /*
                             * Append data into localMap to cache
                             */
                            localMap.put(added, session);
                        }
                        LOGGER.info(RedisMsg.RS_AFTER, finalSession.id(),
                                null == oldSession ? null : oldSession.id());
                    } else {
                        /*
                         * ERROR throw out
                         */
                        res2.cause().printStackTrace();
                        handler.handle(Future.failedFuture(res.cause()));
                    }
                });
            } else {
                /*
                 * ERROR throw out
                 */
                res.cause().printStackTrace();
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    private void writeSession(final Session session, final Handler<AsyncResult<String>> handler) {
        /* Write buffer */
        final Buffer buffer = Buffer.buffer();
        final RedisSession sessionImpl = (RedisSession) session;
        sessionImpl.writeToBuffer(buffer);

        /* Synced timeout here */
        final SetOptions options = new SetOptions().setPX(session.timeout());
        final String key = sessionImpl.id();
        client.setBinaryWithOptions(key, buffer, options, res -> {
            if (res.succeeded()) {
                LOGGER.info(RedisMsg.RD_KEYS, Ut.fromJoin(session.data().keySet()));
                /*
                 * Add data if this id does not exist
                 */
                handler.handle(Future.succeededFuture(key));
            } else {
                /*
                 * ERROR throw out
                 */
                res.cause().printStackTrace();
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
    }
}
