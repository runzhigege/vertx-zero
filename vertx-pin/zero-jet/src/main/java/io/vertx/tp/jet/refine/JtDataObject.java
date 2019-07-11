package io.vertx.tp.jet.refine;

import cn.vertxup.jet.domain.tables.pojos.IApi;
import cn.vertxup.jet.domain.tables.pojos.IJob;
import cn.vertxup.jet.domain.tables.pojos.IService;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.atom.JtApp;
import io.vertx.tp.jet.cv.JtConstant;
import io.vertx.tp.jet.cv.em.WorkerType;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.zero.atom.Database;
import io.vertx.zero.atom.Integration;
import io.vertx.zero.epic.Ut;
import io.vertx.zero.epic.fn.Fn;

import java.util.function.Supplier;

class JtDataObject {

    static Integration toIntegration(final Supplier<String> supplier) {
        final JsonObject data = Ut.toJObject(supplier.get());
        /*
         * Integration data here.
         */
        final Integration integration = new Integration();
        integration.fromJson(data);
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

    @SuppressWarnings("all")
    static JsonObject toOptions(final JtApp app, final IApi api, final IService service) {
        final JsonObject options = toOptions(app, service);
        // TODO: Api configuration
        return options;
    }

    @SuppressWarnings("all")
    static JsonObject toOptions(final JtApp app, final IJob job, final IService service) {
        final JsonObject options = toOptions(app, service);
        // TODO: Job configuration
        return options;
    }

    private static JsonObject toOptions(final JtApp app, final IService service) {
        final JsonObject options = Ut.toJObject(service.getConfigService());
        {
            /* default options, you can add more */
            options.put(KeField.NAME, app.getName());
            options.put(KeField.IDENTIFIER, service.getIdentifier());
        }
        return options;
    }

    static void initApi(final IApi api) {
        /*
         * Set default value in I_API related to worker
         * workerType
         * workerAddress
         * workerConsumer
         * workerClass
         * workerJs
         */
        Fn.safeSemi(Ut.isNil(api.getWorkerClass()),
                () -> api.setWorkerClass(JtConstant.COMPONENT_DEFAULT_WORKER.getName()));
        Fn.safeSemi(Ut.isNil(api.getWorkerAddress()),
                () -> api.setWorkerAddress(JtConstant.EVENT_ADDRESS));
        Fn.safeSemi(Ut.isNil(api.getWorkerConsumer()),
                () -> api.setWorkerConsumer(JtConstant.COMPONENT_DEFAULT_CONSUMER.getName()));
        Fn.safeSemi(Ut.isNil(api.getWorkerType()),
                () -> api.setWorkerType(WorkerType.STD.name()));
    }
}
