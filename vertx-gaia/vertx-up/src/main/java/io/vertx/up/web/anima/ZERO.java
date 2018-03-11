package io.vertx.up.web.anima;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String INFIX_NULL = "[ ZERO ] The system scanned null infix for key = {0} " +
            "on the field \"{1}\" of {2}";

    String INFIX_IMPL = "[ ZERO ] The hitted class {0} does not implement the interface" +
            "of {1}";

    String SCANED_RULE = "[ ZERO ] ( {0} Rules ) Zero system scanned the folder /codex/ " +
            "to pickup {0} rule definition files.";

}

interface Pool {
    ConcurrentMap<Class<?>, AffluxInfix> INFIXES = new ConcurrentHashMap<>();

    ConcurrentMap<Class<?>, InfixPlugin> PLUGINS = new ConcurrentHashMap<>();
}
