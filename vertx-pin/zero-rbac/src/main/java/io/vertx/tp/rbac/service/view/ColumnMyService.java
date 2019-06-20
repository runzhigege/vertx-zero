package io.vertx.tp.rbac.service.view;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.extension.view.AbstractColumnMy;

public class ColumnMyService extends AbstractColumnMy {
    @Override
    public Future<JsonArray> fetchMy(final JsonObject config) {
        return null;
    }

    @Override
    public Future<JsonArray> saveMy(final String resourceId, final JsonArray projection) {
        return null;
    }
}
