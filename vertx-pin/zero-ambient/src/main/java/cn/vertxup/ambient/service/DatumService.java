package cn.vertxup.ambient.service;

import cn.vertxup.ambient.domain.tables.daos.XCategoryDao;
import cn.vertxup.ambient.domain.tables.daos.XTabularDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.refine.At;
import io.vertx.up.aiki.Ux;

public class DatumService implements DatumStub {

    @Override
    public Future<JsonArray> tabulars(final String appId, final String type) {
        return fetchArray(XTabularDao.class, At.filters(appId, type, null));
    }

    @Override
    public Future<JsonArray> categories(final String appId, final String type) {
        return fetchArray(XCategoryDao.class, At.filters(appId, type, null));
    }

    @Override
    public Future<JsonObject> tabular(final String appId, final String type, final String code) {
        return Ux.Jooq.on(XTabularDao.class)
                .fetchOneAsync(At.filters(appId, type, code))
                .compose(Ux::fnJObject);
    }

    @Override
    public Future<JsonObject> category(final String appId, final String type, final String code) {
        return Ux.Jooq.on(XCategoryDao.class)
                .fetchOneAsync(At.filters(appId, type, code))
                .compose(Ux::fnJObject);
    }

    @Override
    public Future<JsonArray> tabulars(final String appId, final JsonArray types) {
        return fetchArray(XTabularDao.class, At.filters(appId, types, null));
    }

    @Override
    public Future<JsonArray> categories(final String appId, final JsonArray types) {
        return fetchArray(XCategoryDao.class, At.filters(appId, types, null));
    }

    private Future<JsonArray> fetchArray(final Class<?> daoCls, final JsonObject filters) {
        return Ux.Jooq.on(daoCls).fetchAndAsync(filters).compose(Ux::fnJArray);
    }
}
