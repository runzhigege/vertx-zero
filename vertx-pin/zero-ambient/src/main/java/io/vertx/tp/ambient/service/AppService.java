package io.vertx.tp.ambient.service;

import cn.vertxup.ambient.tables.daos.XAppDao;
import cn.vertxup.ambient.tables.pojos.XApp;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.shared.Ke;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;

public class AppService implements AppStub {

    @Override
    public Future<JsonObject> fetchByName(final String name) {
        return Ux.Jooq.on(XAppDao.class)
                /* Fetch By Name */
                .<XApp>fetchOneAsync("name", name)
                /* Convert to Json */
                .compose(Ux::fnJObject)
                /* Before App Initialized ( Public Api ) */
                .compose(appData -> Uson.create(appData).remove("appKey").toFuture())
                /* Image field: logo */
                .compose(Ke.image("logo"));
    }
}
