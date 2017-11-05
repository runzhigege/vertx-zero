package com.vie.fun.error;

import com.vie.hors.ZeroException;

@FunctionalInterface
public interface JdSupplier<T> {

    T get() throws ZeroException;
}
