package io.vertx.tp.jet;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {
    ConcurrentMap<Class<?>, JetThanatos> ENSURERS =
            new ConcurrentHashMap<>();
}
