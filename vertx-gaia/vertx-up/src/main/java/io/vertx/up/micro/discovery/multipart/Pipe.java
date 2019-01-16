package io.vertx.up.micro.discovery.multipart;

import io.vertx.core.Handler;

/**
 * Pump definition for fix issue
 */
public interface Pipe<T> {

    void doRequest(Handler<T> handler);
}
