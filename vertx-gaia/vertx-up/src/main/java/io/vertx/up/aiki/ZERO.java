package io.vertx.up.aiki;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String MONGO_FILTER = "[ ZERO ] ( Mongo -> findOne ) collection = {0}, filter = {1}, result = {2}.";
    String MONGO_INSERT = "[ ZERO ] ( Mongo -> insert ) collection = {0}, data = {1}.";
    String MONGO_UPDATE = "[ ZERO ] ( Mongo -> findOneAndReplace ) collection = {0}, filter = {1}, result = {2}.";
    String MONGO_DELETE = "[ ZERO ] ( Mongo -> removeDocument ) Effected: {2} Rows. collection = {0}, filter = {1}.";
    String MONGO_FIND = "[ ZERO ] ( Mongo -> findWithOptions ) collection = {0}, filter = {1}, options = {2}, result = {3}.";

    String RPC_RESULT = "[ ZERO ] ( Rpc -> thenRpc ) Client = {4}, Ipc ( {0},{1} ) with params {2}, response data is {3}.";
    String JOOQ_PARSE = "[ ZERO ] ( Jooq -> Condition ) Parsed result is \ncondition = \n{0}.";
    String JOOQ_BIND = "[ ZERO ] ( Pojo Bind ) Pojo up.god.file = {0} has been bind to dao {1}, Field mode enabled.";
    String JOOQ_FIELD = "[ ZERO ] ( Pojo ) The field \"{0}\" has been hitted ( converted ) to \"{1}\"";
    String JOOQ_MOJO = "[ ZERO ] ( Pojo ) The analyzed result should be : Revert {0}, Columns = {1}";

    String INQUIRY_MESSAGE = "[ ZERO ] ( Inquiry ) Processed metadata = {0}.";

    String POOL_PUT = "[ ZERO ] ( Shared ) key = {0}, value = {1} has been put into {2}.";
    String POOL_PUT_TIMER = "[ ZERO ] ( Shared ) key = {0}, value = {1} has been put into {2} to keep {3} seconds";
    String POOL_REMOVE = "[ ZERO ] ( Shared ) key = {0} has been removed from pool name = {2}.";
    String POOL_GET = "[ ZERO ] ( Shared ) key = {0} has been picked from {1}, mode = {2}";
}

interface Pool {

    ConcurrentMap<Class<?>, UxJooq> JOOQ = new ConcurrentHashMap<>();

    ConcurrentMap<String, UxPool> POOL = new ConcurrentHashMap<>();

    ConcurrentMap<Class<?>, Uobj> INJECTION = new ConcurrentHashMap<>();
}
