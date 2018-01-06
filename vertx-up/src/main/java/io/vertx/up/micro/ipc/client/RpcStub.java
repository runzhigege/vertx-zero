package io.vertx.up.micro.ipc.client;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;

/**
 * Different implementation by type.
 */
public interface RpcStub {
    /**
     * Rpc Logical
     *
     * @param data
     */
    Future<Envelop> send(Vertx vertx, IpcData data);
}
