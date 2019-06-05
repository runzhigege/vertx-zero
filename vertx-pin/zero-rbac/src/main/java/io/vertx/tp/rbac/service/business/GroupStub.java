package io.vertx.tp.rbac.service.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

/*
 * Basic Group interface
 *
 */
public interface GroupStub {
    /**
     * R_GROUP_ROLE
     * <p>
     * groupKey -> Relation to role
     */
    Future<JsonArray> fetchRoleIds(String groupKey);
}
