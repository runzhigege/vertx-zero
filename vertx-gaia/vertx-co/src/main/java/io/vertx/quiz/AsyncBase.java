package io.vertx.quiz;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.commune.config.Database;
import io.vertx.up.commune.config.Integration;

import java.util.function.Consumer;

public class AsyncBase extends ZeroBase {

    public <T> void async(final TestContext context,
                          final Future<T> future,
                          final Consumer<T> consumer) {
        Async.async(context, future, consumer);
    }

    protected Database database(final String filename) {
        final JsonObject fileJson = this.ioJObject(filename);
        final Database database = new Database();
        database.fromJson(fileJson);
        return database;
    }

    protected Integration integration(final String filename) {
        final JsonObject fileJson = this.ioJObject(filename);
        final Integration integration = new Integration();
        integration.fromJson(fileJson);
        return integration;
    }
}
