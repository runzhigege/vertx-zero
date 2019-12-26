package io.vertx.tp.optic;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface Trash {
    /*
     * Backup data to history database here
     */
    Future<JsonObject> backupAsync(String identifier, JsonObject data);
}
