package io.vertx.zero.fn;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface ZeroActuator {

    void execute() throws ZeroException;
}
