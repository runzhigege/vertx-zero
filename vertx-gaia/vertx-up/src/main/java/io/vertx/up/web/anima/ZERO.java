package io.vertx.up.web.anima;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {

    ConcurrentMap<Class<?>, InfixPlugin> PLUGINS = new ConcurrentHashMap<>();
}
