package io.vertx.tp.ui.service.column;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

class StoreValve implements UiValve {
    @Override
    public Future<JsonArray> fetchColumn(final String identifier, final String sigma) {
        // TODO: Dynamic
        return null;
    }
}
