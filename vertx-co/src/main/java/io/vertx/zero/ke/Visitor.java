package io.vertx.zero.ke;

import com.vie.hors.ZeroException;

public interface Visitor<T> {
    /**
     * @return
     * @throws ZeroException
     */
    T visit(String... keys) throws ZeroException;
}
