package io.vertx.up.epic.fn;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface ZeroActuator {

    void execute() throws ZeroException;
}
