package io.vertx.tp.plugin.redis;

interface RedisMsg {

    String RD_CLOSE = "[ ZERO ] ( Redis ) The client has been closed successfully !";
    String RD_CLEAR = "[ ZERO ] ( Redis ) The client has been removed: {0}";
    String RD_KEYS = "[ ZERO ] ( Redis ) Set key: {0}";
    String RD_OPTS = "[ ZERO ] ( Redis ) Connect to Endpoint ( {0} ) Options read: {1}";
    String RS_MESSAGE = "[ ZERO ] ( Redis ) Call {1} method: id = {0}";
}
