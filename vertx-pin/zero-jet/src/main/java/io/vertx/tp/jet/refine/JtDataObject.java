package io.vertx.tp.jet.refine;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.atom.Database;
import io.vertx.zero.atom.Integration;
import io.vertx.zero.atom.IntegrationRequest;
import io.zero.epic.Ut;

import java.util.function.Supplier;

class JtDataObject {

    static Integration toIntegration(final Supplier<String> supplier) {
        final JsonObject data = Ut.toJObject(supplier.get());
        /*
         * Integration data here.
         */
        final Integration integration = new Integration();
        integration.setEndpoint(data.getString("endpoint"));
        integration.setHostname(data.getString("hostname"));
        integration.setUsername(data.getString("username"));
        integration.setPassword(data.getString("password"));
        integration.setPort(data.getInteger("port"));
        /*
         * Integration Request
         */
        final JsonObject apis = data.getJsonObject("apis");
        Ut.<JsonObject>itJObject(apis, (json, field) -> {
            final IntegrationRequest request = Ut.deserialize(json, IntegrationRequest.class);
            integration.getApis().put(field, request);
        });
        /*
         * SSL Options
         */
        // TODO: SSL Options
        return integration;
    }

    static Database toDatabase(final Supplier<String> supplier, final Database defaultDatabase) {

        final JsonObject databaseJson = Ut.toJObject(supplier.get());
        if (Ut.isNil(databaseJson)) {
            /*
             * Current app.
             */
            return defaultDatabase;
        } else {
            /*
             * Api `configDatabase` first
             */
            final Database database = new Database();
            database.fromJson(databaseJson);
            return database;
        }
    }
}
