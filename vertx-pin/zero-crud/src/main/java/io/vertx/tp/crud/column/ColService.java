package io.vertx.tp.crud.column;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/*
 * Default Implementation
 */
public class ColService extends AbstractColumn {

    @Override
    public Future<JsonArray> fetchMyColumns(final JsonObject filters) {
        this.getLogger().info("[ Εκδήλωση ] My Columns = {0}", filters.encode());
        return null;
    }

    @Override
    public Future<JsonArray> saveMyColumns(final JsonObject filters) {
        this.getLogger().info("[ Εκδήλωση ] Save My Columns = {0}", filters.encode());
        return null;
    }

    @Override
    public Future<JsonArray> fetchFullColumns(final JsonObject filters) {
        this.getLogger().info("[ Εκδήλωση ] Full Columns = {0}", filters.encode());
        return null;
    }
}
