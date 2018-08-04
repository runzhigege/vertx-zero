package io.vertx.zero.exception;

public class JooqVertxNullException extends UpException {

    public JooqVertxNullException(
            final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40060;
    }
}
