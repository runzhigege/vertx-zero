package io.vertx.tp.rbac.service.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

import java.util.List;

/*
 * Relation calculation service for
 * 1) User - Role
 * 2) Group - Role
 * 3) User - Groups ( Standalone / Inherit )
 */
public interface LinkStub {
    /**
     * Fetch roles by user key
     * R_USER_ROLE table record as relation
     * 1) full = true: Find all role entities
     * 2) full = false: Find role ids only
     */
    Future<List<String>> fetchRoleIds(String userKey);

    Future<JsonArray> fetchRoles(String userKey);

    /**
     * Fetch groups by user key
     * R_USER_GROUP table record as relation
     * 1) full = true: Find all group entities
     * 2) full = false: Find group ids only
     */
    Future<List<String>> fetchGroupIds(String userKey);

    Future<JsonArray> fetchGroups(String userKey);
}
