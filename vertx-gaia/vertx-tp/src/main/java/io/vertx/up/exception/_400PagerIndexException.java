package io.vertx.up.exception;

public class _400PagerIndexException extends WebException {

    public _400PagerIndexException(final Class<?> clazz,
                                   final Integer page) {
        super(clazz, page);
    }

    @Override
    public int getCode() {
        return -60025;
    }
}
