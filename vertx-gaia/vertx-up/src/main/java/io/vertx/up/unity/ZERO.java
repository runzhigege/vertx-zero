package io.vertx.up.unity;

import io.vertx.up.unity.jq.UxJooq;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String MONGO_FILTER = "[ ZERO ] ( Mongo -> findOne ) collection = {0}, filter = {1}, result = {2}.";
    String MONGO_INSERT = "[ ZERO ] ( Mongo -> insert ) collection = {0}, data = {1}.";
    String MONGO_UPDATE = "[ ZERO ] ( Mongo -> findOneAndReplace ) collection = {0}, filter = {1}, result = {2}.";
    String MONGO_DELETE = "[ ZERO ] ( Mongo -> removeDocument ) Effected: {2} Rows. collection = {0}, filter = {1}.";
    String MONGO_FIND = "[ ZERO ] ( Mongo -> findWithOptions ) collection = {0}, filter = {1}, options = {2}, result = {3}.";

    String RPC_RESULT = "[ ZERO ] ( Rpc -> thenRpc ) Client = {4}, Ipc ( {0},{1} ) with params {2}, response data is {3}.";

    String POOL_PUT = "[ ZERO ] ( Shared ) key = {0}, value = {1} has been put into {2}.";
    String POOL_PUT_TIMER = "[ ZERO ] ( Shared ) key = {0}, value = {1} has been put into {2} to keep {3} seconds";
    String POOL_REMOVE = "[ ZERO ] ( Shared ) key = {0} has been removed from pool name = {2}.";
    String POOL_GET = "[ ZERO ] ( Shared ) key = {0} has been picked from {1}, mode = {2}";
    String POOL_CLEAR = "[ ZERO ] ( Shared ) pool = {0} has been cleared successfully.";

    String JOB_START = "[ ZERO ] ( UxJob ) The job {0} has been started with timeId: {1}.";
    String JOB_STOP = "[ ZERO ] ( UxJob ) The job {0} has been stopped and timeId: {1} removed.";
    String JOB_RESUME = "[ ZERO ] ( UxJob ) The job {0} will be resume and new timeId generated: {1}.";
}

interface Pool {

    ConcurrentMap<Class<?>, UxJooq> JOOQ_POOL = new ConcurrentHashMap<>();

    ConcurrentMap<Class<?>, UxJooq> JOOQ_POOL_HIS = new ConcurrentHashMap<>();

    ConcurrentMap<String, UxPool> POOL = new ConcurrentHashMap<>();
}
