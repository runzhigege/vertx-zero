package com.vie.hors.system;

import com.vie.hors.DemonException;

public class ArgumentException extends DemonException {

    public ArgumentException(final Class<?> clazz,
                             final String method,
                             final Integer length) {
        super(clazz, method, clazz.getName(), length);
    }

    @Override
    public int getCode() {
        return -10001;
    }
}
