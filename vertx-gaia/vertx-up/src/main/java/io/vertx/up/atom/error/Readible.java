package io.vertx.up.atom.error;

import io.vertx.up.exception.WebException;
import io.vertx.zero.epic.Ut;

/**
 * Fill the field "readible" for input exception
 */
public interface Readible {
    /**
     * Get code readible
     *
     * @return
     */
    static Readible get() {
        return Ut.singleton(CodeReadible.class);
    }

    /**
     * Fill the field "readible" for web exception
     *
     * @param error
     * @return
     */
    void interpret(WebException error);
}
