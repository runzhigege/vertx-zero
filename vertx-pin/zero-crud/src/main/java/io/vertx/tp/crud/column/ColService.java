package io.vertx.tp.crud.column;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.refine.Ix;

/*
 * Default Implementation
 */
public class ColService extends AbstractColumn {

    @Override
    public Future<JsonArray> fetchMyColumns(final JsonObject filters) {
        Ix.infoFilters(this.getLogger(), "My Columns = {0}", filters.encode());
        return null;
    }

    @Override
    public Future<JsonArray> saveMyColumns(final JsonObject filters) {
        Ix.infoFilters(this.getLogger(), "Save My Columns = {0}", filters.encode());
        return null;
    }

    @Override
    public Future<JsonArray> fetchFullColumns(final JsonObject filters) {
        Ix.infoFilters(this.getLogger(), "Full Columns = {0}", filters.encode());
        return null;
    }
}
