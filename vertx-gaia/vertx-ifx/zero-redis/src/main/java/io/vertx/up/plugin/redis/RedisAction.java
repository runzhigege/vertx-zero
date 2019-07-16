package io.vertx.up.plugin.redis;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.web.Session;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import io.vertx.redis.op.SetOptions;

/*
 * Session operation
 */
class RedisAction {

    private final transient RedisClient client;
    private final transient Vertx vertx;

    RedisAction(final Vertx vertx, final JsonObject optionsJson) {
        this.vertx = vertx;
        final RedisOptions options = new RedisOptions(optionsJson);
        client = RedisClient.create(vertx, options);
    }

    void close(final Handler<AsyncResult<Void>> handler) {
        client.close(handler);
    }

    void remove(final String sessionId, final Handler<AsyncResult<Long>> handler) {
        client.del(sessionId, handler);
    }

    void get(final String sessionId, final Handler<AsyncResult<Buffer>> handler) {
        client.getBinary(sessionId, queried -> {
            if (queried.succeeded()) {
                final Buffer buffer = queried.result();
                handler.handle(Future.succeededFuture(buffer));
            } else {
                handler.handle(Future.failedFuture(queried.cause()));
            }
        });
    }

    void writeSession(final Session session, final Handler<AsyncResult<Void>> handler) {

        final Buffer buffer = Buffer.buffer();
        final RedisSession sessionImpl = (RedisSession) session;
        /*
         * Session serialized to buffer
         */
        sessionImpl.writeToBuffer(buffer);

        final SetOptions options = new SetOptions().setPX(session.timeout());
        client.setBinaryWithOptions(session.id(), buffer, options, handler);
    }

    RedisSession readSession(final Buffer buffer) {
        final RedisSession sessionImpl = new RedisSession(new PRNG(vertx));
        sessionImpl.readFromBuffer(0, buffer);
        return sessionImpl;
    }
}
