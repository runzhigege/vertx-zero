package io.vertx.up.unity;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String MONGO_FILTER = "[ ZERO ] ( Mongo -> findOne ) collection = {0}, filter = {1}, result = {2}.";
    String MONGO_INSERT = "[ ZERO ] ( Mongo -> insert ) collection = {0}, data = {1}.";
    String MONGO_UPDATE = "[ ZERO ] ( Mongo -> findOneAndReplace ) collection = {0}, filter = {1}, result = {2}.";
    String MONGO_DELETE = "[ ZERO ] ( Mongo -> removeDocument ) Effected: {2} Rows. collection = {0}, filter = {1}.";
    String MONGO_FIND = "[ ZERO ] ( Mongo -> findWithOptions ) collection = {0}, filter = {1}, options = {2}, result = {3}.";

    String RPC_RESULT = "[ ZERO ] ( Rpc -> thenRpc ) Client = {4}, Ipc ( {0},{1} ) with params {2}, response data is {3}.";
    String JOOQ_BIND = "[ ZERO ] ( Pojo Bind ) Pojo up.god.file = {0} has been bind to dao {1}, Field mode enabled.";
    String JOOQ_FIELD = "[ ZERO ] ( Pojo ) The field \"{0}\" has been hitted ( converted ) to \"{1}\"";
    String JOOQ_MOJO = "[ ZERO ] ( Pojo ) The analyzed result should be : Revert {0}";

    String INQUIRY_MESSAGE = "[ ZERO ] ( Inquiry ) Processed metadata = {0}.";

    String POOL_PUT = "[ ZERO ] ( Shared ) key = {0}, value = {1} has been put into {2}.";
    String POOL_PUT_TIMER = "[ ZERO ] ( Shared ) key = {0}, value = {1} has been put into {2} to keep {3} seconds";
    String POOL_REMOVE = "[ ZERO ] ( Shared ) key = {0} has been removed from pool name = {2}.";
    String POOL_GET = "[ ZERO ] ( Shared ) key = {0} has been picked from {1}, mode = {2}";
    String POOL_CLEAR = "[ ZERO ] ( Shared ) pool = {0} has been cleared successfully.";
    String INFIX_NULL = "[ ZERO ] The system scanned null infix for key = {0} " +
            "on the field \"{1}\" of {2}";

    String INFIX_IMPL = "[ ZERO ] The hitted class {0} does not implement the interface" +
            "of {1}";

    String JOB_START = "[ ZERO ] ( UxJob ) The job {0} has been started with timeId: {1}.";
    String JOB_STOP = "[ ZERO ] ( UxJob ) The job {0} has been stopped and timeId: {1} removed.";
    String JOB_RESUME = "[ ZERO ] ( UxJob ) The job {0} will be resume and new timeId generated: {1}.";
}

interface Pool {

    ConcurrentMap<Class<?>, UxJooq> JOOQ_POOL = new ConcurrentHashMap<>();

    ConcurrentMap<Class<?>, UxJooq> JOOQ_POOL_HIS = new ConcurrentHashMap<>();

    ConcurrentMap<String, UxPool> POOL = new ConcurrentHashMap<>();

    ConcurrentMap<Class<?>, Uobj> INJECTION = new ConcurrentHashMap<>();

    ConcurrentMap<Class<?>, Uimmit> INFIXES = new ConcurrentHashMap<>();
}
