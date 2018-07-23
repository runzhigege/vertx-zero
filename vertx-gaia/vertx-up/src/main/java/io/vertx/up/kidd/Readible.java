package io.vertx.up.kidd;

import io.vertx.up.epic.mirror.Instance;
import io.vertx.up.exception.WebException;
import io.vertx.up.kidd.outcome.CodeReadible;

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
        return Instance.singleton(CodeReadible.class);
    }

    /**
     * Fill the field "readible" for web exception
     *
     * @param error
     * @return
     */
    void interpret(WebException error);
}
