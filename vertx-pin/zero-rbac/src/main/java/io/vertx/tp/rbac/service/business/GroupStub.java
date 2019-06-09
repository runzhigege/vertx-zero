package io.vertx.tp.rbac.service.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

import java.util.List;

/*
 * Basic Group interface
 *
 */
public interface GroupStub {
    /*
     * Fetch all groups include all parent groups
     */
    Future<JsonArray> fetchFull(String groupId);

    /*
     * Fetch all groups include all parent groups
     */
    Future<JsonArray> fetchFull(List<String> groupIds);
}
