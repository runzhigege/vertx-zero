package cn.vertxup.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface LoginStub {
    /*
     * Login workflow
     * {
     *      "username": "xxx",
     *      "password": "xxx"
     * }
     */
    Future<JsonObject> execute(final String username, final String password);
}
