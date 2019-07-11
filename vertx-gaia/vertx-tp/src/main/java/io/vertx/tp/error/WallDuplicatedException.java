package io.vertx.tp.error;

import io.vertx.zero.exception.UpException;
import io.vertx.zero.epic.Ut;

import java.util.Set;

public class WallDuplicatedException extends UpException {

    public WallDuplicatedException(final Class<?> classes,
                                   final Set<String> classNames) {
        super(classes, Ut.fromJoin(classNames));
    }

    @Override
    public int getCode() {
        return -40038;
    }
}
