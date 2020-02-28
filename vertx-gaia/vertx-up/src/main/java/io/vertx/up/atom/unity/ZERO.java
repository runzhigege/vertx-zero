package io.vertx.up.atom.unity;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {
    String INFIX_NULL = "[ ZERO ] The system scanned null infix for key = {0} " +
            "on the field \"{1}\" of {2}";

    String INFIX_IMPL = "[ ZERO ] The hitted class {0} does not implement the interface" +
            "of {1}";
}

interface Pool {

    ConcurrentMap<Class<?>, Uobj> INJECTION = new ConcurrentHashMap<>();

    ConcurrentMap<Class<?>, Uimmit> INFIXES = new ConcurrentHashMap<>();
}
