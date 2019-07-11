package io.vertx.up.micro.config;

import io.vertx.up.eon.em.ServerType;

public class RxServerVisitor extends HttpServerVisitor {

    @Override
    protected ServerType getType() {
        return ServerType.RX;
    }
}
