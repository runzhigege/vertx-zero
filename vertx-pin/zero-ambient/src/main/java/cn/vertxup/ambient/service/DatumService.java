package cn.vertxup.ambient.service;

import cn.vertxup.ambient.domain.tables.daos.XCategoryDao;
import cn.vertxup.ambient.domain.tables.daos.XNumberDao;
import cn.vertxup.ambient.domain.tables.daos.XTabularDao;
import cn.vertxup.ambient.domain.tables.pojos.XNumber;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.refine.At;
import io.vertx.up.unity.Ux;

public class DatumService implements DatumStub {

    @Override
    public Future<JsonArray> tabulars(final String appId, final String type) {
        return this.fetchArray(XTabularDao.class, At.filters(appId, type, null));
    }

    @Override
    public Future<JsonArray> categories(final String appId, final String type) {
        return this.fetchArray(XCategoryDao.class, At.filters(appId, type, null));
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
        return this.fetchArray(XTabularDao.class, At.filters(appId, types, null));
    }

    @Override
    public Future<JsonArray> categories(final String appId, final JsonArray types) {
        return this.fetchArray(XCategoryDao.class, At.filters(appId, types, null));
    }

    @Override
    public Future<JsonArray> numbers(final String appId, final String code, final Integer count) {
        final JsonObject filters = new JsonObject();
        filters.put("appId", appId);
        filters.put("code", code);
        return this.numbers(filters, count);
    }

    @Override
    public Future<JsonArray> numbersBySigma(final String sigma, final String code, final Integer count) {
        At.infoFlow(this.getClass(), "Number parameters: sigma = {0}, code = {1}, count = {2}",
                sigma, code, count);
        final JsonObject filters = new JsonObject();
        filters.put("sigma", sigma);
        filters.put("code", code);
        return this.numbers(filters, count);
    }

    private Future<JsonArray> numbers(final JsonObject filters, final Integer count) {
        /*
         * XNumber processing
         */
        return Ux.Jooq.on(XNumberDao.class)
                .<XNumber>fetchOneAsync(filters)
                .compose(number -> Ux.Jooq.on(XNumberDao.class)
                        /*
                         * Pre process for number generation here.
                         */
                        .updateAsync(number.setCurrent(number.getCurrent() + count)))
                .compose(number -> At.serialsAsync(number, count))
                .compose(generation -> Ux.future(new JsonArray(generation)))
                .otherwise(Ux.otherwise(JsonArray::new));
    }

    private Future<JsonArray> fetchArray(final Class<?> daoCls, final JsonObject filters) {
        return Ux.Jooq.on(daoCls).fetchAndAsync(filters).compose(Ux::fnJArray);
    }
}
