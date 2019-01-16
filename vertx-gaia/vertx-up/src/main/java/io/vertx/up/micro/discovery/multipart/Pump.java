package io.vertx.up.micro.discovery.multipart;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Pump definition for fix issue
 */
public interface Pump<T> {

    void doRequest(Handler<AsyncResult<T>> handler);
}
