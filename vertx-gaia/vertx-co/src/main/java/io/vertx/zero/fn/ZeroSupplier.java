package io.vertx.zero.fn;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface ZeroSupplier<T> {

    T get() throws ZeroException;
}
