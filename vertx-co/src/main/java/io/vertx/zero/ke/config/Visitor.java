package io.vertx.zero.ke.config;

/**
 * Resource visitor to read config files.
 */
public interface Visitor<T> {
    /**
     * Read config store options
     * @return
     */
    T visit();
}
