package io.vertx.up.plugin.redis;

interface RedisMsg {

    String RD_CLIENT = "[ ZERO ] ( Redis ) The client has been closed successfully !";
    String RD_CLEAR = "[ ZERO ] ( Redis ) The client has been removed: {0}";
    String RD_KEYS = "[ ZERO ] ( Redis ) Set key: {0}";
}
