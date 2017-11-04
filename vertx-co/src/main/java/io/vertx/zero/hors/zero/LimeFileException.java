package io.vertx.zero.hors.zero;

import com.vie.hors.DemonException;

/**
 * vertx:
 * lime: error, inject, db, consul
 */
public class LimeFileException extends DemonException {

    public LimeFileException(final Class<?> clazz, final String filename) {
        super(clazz, filename);
    }

    @Override
    public int getCode() {
        return -20001;
    }
}
