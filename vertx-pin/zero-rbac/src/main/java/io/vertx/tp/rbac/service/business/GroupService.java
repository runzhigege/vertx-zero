package io.vertx.tp.rbac.service.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

import java.util.List;

public class GroupService implements GroupStub {
    @Override
    public Future<JsonArray> fetchFull(final String groupId) {
        return null;
    }

    @Override
    public Future<JsonArray> fetchFull(final List<String> groupIds) {
        return null;
    }
}
