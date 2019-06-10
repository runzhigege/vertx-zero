package io.vertx.tp.rbac.service.business;

import cn.vertxup.domain.tables.pojos.SGroup;
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
    Future<JsonArray> fetchRoleIdsAsync(String groupKey);

    JsonArray fetchRoleIds(String groupKey);

    SGroup fetchParent(String groupKey);
}
