package com.vie.util;

import io.vertx.config.ConfigStoreOptions;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Support values
 * event-bus,
 * file,
 * json,
 * http,
 * env,
 * sys,
 * directory
 */
enum StoreType {
    JSON("json"),
    FILE("file"),
    HTTP("http"),
    ENV("env"),
    SYS("sys"),
    DIRECTORY("directory"),
    EVENT_BUS("event-but");

    StoreType(final String literal) {
        this.literal = literal;
    }

    private final transient String literal;

    public String key() {
        return this.literal;
    }
}

enum StoreFormat {
    YAML("yaml"),
    PROP("properties");

    StoreFormat(final String literal) {
        this.literal = literal;
    }

    private final transient String literal;

    public String key() {
        return this.literal;
    }
}

enum StoreConfig {
    PATH("path");

    StoreConfig(final String literal) {
        this.literal = literal;
    }

    private final transient String literal;

    public String key() {
        return this.literal;
    }
}

interface Storage {
    /**
     * Memory pool for each file of ConfigStoreOptions
     */
    ConcurrentMap<String, ConfigStoreOptions> STORE = new ConcurrentHashMap<>();
    /**
     * Singletons
     */
    ConcurrentMap<String, Object> SINGLETON = new ConcurrentHashMap<>();
    /**
     * Class cached.
     */
    ConcurrentMap<String, Class<?>> CLASSES = new ConcurrentHashMap<>();
}
