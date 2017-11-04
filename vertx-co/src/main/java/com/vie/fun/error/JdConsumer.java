package com.vie.fun.error;

import com.vie.hors.ZeroException;

@FunctionalInterface
public interface JdConsumer {

    void exec() throws ZeroException;
}
