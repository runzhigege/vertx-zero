package io.vertx.tp.crud.connect;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {
    ConcurrentMap<String, IxLinker> LINKER_MAP =
            new ConcurrentHashMap<>();
}
