package io.vertx.tp.rbac.service.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/*
 * User Interface for Login
 */
public interface UserStub {
    /**
     * Fetch roles by user key
     * R_USER_ROLE table record as relation
     */
    Future<JsonArray> fetchRoles(String userKey);

    /**
     * Fetch ouser by client_id
     */
    Future<JsonObject> fetchOUser(String userKey);
}
