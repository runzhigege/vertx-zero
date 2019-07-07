package io.vertx.tp.ambient.service;

import cn.vertxup.ambient.tables.daos.XAppDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.extension.Init;
import io.vertx.tp.ambient.extension.Prerequisite;
import io.vertx.tp.ambient.init.AtPin;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;

import javax.inject.Inject;
import java.util.Objects;

public class InitService implements InitStub {
    @Inject
    private transient AppStub stub;

    @Override
    public Future<JsonObject> initApp(final String appId,
                                      final JsonObject data) {
        /* Default Future */
        return Ux.toFuture(data.put(KeField.KEY, appId))
                /* X_APP initialization */
                .compose(Init.app().apply())
                /* X_SOURCE initialization */
                .compose(Init.source().apply())
                /* Database initialization */
                .compose(Init.database().apply())
                /* Extension initialization */
                .compose(this::initDefined)
                /* Data Loading */
                .compose(Init.data().apply())
                /* Image */
                .compose(Ke.image(KeField.LOGO));
    }

    @Override
    public Future<JsonObject> initExtension(final String appName) {
        /* Fetch App */
        return Ux.Jooq.on(XAppDao.class)
                /* X_APP Fetching */
                .fetchOneAsync(KeField.NAME, appName)
                .compose(Ux::fnJObject)
                /* X_SOURCE fetching, Fetching skip Database initialization */
                .compose(appJson -> this.stub.fetchSource(appJson.getString(KeField.KEY))
                        .compose(source -> Uson.create(appJson).append(KeField.SOURCE, source).toFuture())
                )
                .compose(this::initDefined)
                /* Data Loading */
                .compose(Init.data().apply())
                /* Image */
                .compose(Ke.image(KeField.LOGO));
    }

    @Override
    public Future<JsonObject> prerequisite(final String appName) {
        /* Prerequisite Extension */
        final Prerequisite prerequisite = AtPin.getPrerequisite();
        if (Objects.isNull(prerequisite)) {
            return Ux.toFuture(new JsonObject());
        } else {
            /*
             * Prerequisite for initialization
             */
            return prerequisite.prepare(appName);
        }
    }

    private Future<JsonObject> initDefined(final JsonObject input) {
        final Init initializer = AtPin.getInit();
        if (Objects.isNull(initializer)) {
            return Ux.toFuture(input);
        } else {
            /*
             * Extension for initialization
             * Will call initializer method, it's implemented Init.class ( Interface )
             *  */
            return initializer.apply().apply(input);
        }
    }
}
