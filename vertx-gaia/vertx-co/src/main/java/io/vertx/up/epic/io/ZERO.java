package io.vertx.up.epic.io;

import io.vertx.core.buffer.Buffer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {
    /** **/
    String INF_PATH = "[ZERO] The system class Stream try to data from {0}, got stream: {1}.";
    /** **/
    String INF_CUR = "[ZERO] Current path is scanned by the system, file existing ? {0}.";
    /** **/
    String INF_APATH = "[ZERO] Absolute path is hitted: {0}.";
}

interface Pool {

    ConcurrentMap<String, Buffer> BUFFER_CACHE = new ConcurrentHashMap<>();
}
