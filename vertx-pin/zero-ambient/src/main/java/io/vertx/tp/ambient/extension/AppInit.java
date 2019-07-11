package io.vertx.tp.ambient.extension;

import cn.vertxup.ambient.domain.tables.daos.XAppDao;
import cn.vertxup.ambient.domain.tables.pojos.XApp;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.cv.AtMsg;
import io.vertx.tp.ambient.refine.At;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;
import io.vertx.zero.epic.Ut;

import java.util.function.Function;

/*
 * Application Initialization
 */
class AppInit implements Init {
    private static final Annal LOGGER = Annal.get(AppInit.class);

    @Override
    public Function<JsonObject, Future<JsonObject>> apply() {
        return appJson -> {
            At.infoApp(LOGGER, AtMsg.INIT_APP, appJson.encode());
            /* Deserialization */
            final XApp app = init(appJson);
            return Ux.Jooq.on(XAppDao.class)
                    /*
                     * Init first step: UPSERT ( Insert / Update )
                     */
                    .upsertAsync(whereUnique(appJson), app)
                    .compose(Ux::fnJObject)
                    /*
                     * Result Building
                     */
                    .compose(input -> Ux.toFuture(result(input, appJson)));
        };
    }

    @Override
    public JsonObject result(final JsonObject input,
                             final JsonObject appJson) {
        final JsonObject result = new JsonObject();
        if (!Ut.isNil(appJson)) {
            result.mergeIn(appJson);
        }
        /* Data Source Input */
        if (!Ut.isNil(input)) {
            result.put(KeField.SOURCE, input.getValue(KeField.SOURCE));
        }
        return result;
    }

    @Override
    public JsonObject whereUnique(final JsonObject input) {
        final JsonObject filters = new JsonObject();
        filters.put(KeField.KEY, input.getValue(KeField.KEY));
        return filters;
    }

    private XApp init(final JsonObject input) {
        /* appKey generation */
        if (!input.containsKey(KeField.APP_KEY)) {
            input.put(KeField.APP_KEY, Ut.randomString(64));
        }
        /* logo */
        final JsonArray files = input.getJsonArray(KeField.LOGO);
        if (null != files) {
            input.put(KeField.LOGO, files.encode());
        }
        final XApp app = Ut.deserialize(input.copy(), XApp.class);
        /* active = true */
        app.setActive(Boolean.TRUE);
        return app;
    }
}
