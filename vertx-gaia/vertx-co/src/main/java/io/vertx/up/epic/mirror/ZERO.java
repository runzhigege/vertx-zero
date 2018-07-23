package io.vertx.up.epic.mirror;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Storage {
    /**
     * Singletons
     */
    ConcurrentMap<String, Object> SINGLETON = new ConcurrentHashMap<>();
    /**
     * Class cached.
     */
    ConcurrentMap<String, Class<?>> CLASSES = new ConcurrentHashMap<>();
}

interface Info {

    String PACKAGES = "[ ZERO ] Zero system scanned {0}/{1} packages.";

    String CLASSES = "[ ZERO ] Zero system scanned {0} classes under package {1}.";
}