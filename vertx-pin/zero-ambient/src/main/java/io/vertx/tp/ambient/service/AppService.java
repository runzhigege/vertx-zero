package io.vertx.tp.ambient.service;

import cn.vertxup.ambient.tables.daos.XAppDao;
import cn.vertxup.ambient.tables.daos.XMenuDao;
import cn.vertxup.ambient.tables.daos.XSourceDao;
import cn.vertxup.ambient.tables.pojos.XApp;
import cn.vertxup.ambient.tables.pojos.XMenu;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.tool.Ke;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;

public class AppService implements AppStub {

    private static final Annal LOGGER = Annal.get(AppService.class);

    @Override
    public Future<JsonObject> fetchByName(final String name) {
        return Ux.Jooq.on(XAppDao.class)
                /* Fetch By Name */
                .<XApp>fetchOneAsync(KeField.NAME, name)
                /* Convert to Json */
                .compose(Ux::fnJObject)
                /* Before App Initialized ( Public Api ) */
                .compose(appData -> Uson.create(appData).remove(KeField.APP_KEY).toFuture())
                /* Image field: logo */
                .compose(Ke.image(KeField.LOGO));
    }

    @Override
    public Future<JsonArray> fetchMenus(final String appId) {
        return Ux.Jooq.on(XMenuDao.class)
                /* Fetch by appId */
                .<XMenu>fetchAsync(KeField.APP_ID, appId)
                /* Get Result */
                .compose(Ux::fnJArray);
    }

    @Override
    public Future<JsonObject> fetchSource(final String appId) {
        return Ux.Jooq.on(XSourceDao.class)
                /* Fetch One by appId */
                .fetchOneAsync(KeField.APP_ID, appId)
                /* Get Result */
                .compose(Ux::fnJObject);
    }
}
