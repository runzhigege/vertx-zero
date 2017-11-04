package com.vie.fun.error;

import com.vie.hors.ZeroException;

@FunctionalInterface
public interface JdUnaryOperator<T> {
    
    T apply(T in) throws ZeroException;
}
