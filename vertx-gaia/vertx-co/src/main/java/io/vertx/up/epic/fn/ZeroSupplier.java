package io.vertx.up.epic.fn;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface ZeroSupplier<T> {

    T get() throws ZeroException;
}
