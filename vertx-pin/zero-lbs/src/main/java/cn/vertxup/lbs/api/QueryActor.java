package cn.vertxup.lbs.api;

import cn.vertxup.lbs.domain.tables.daos.LCityDao;
import cn.vertxup.lbs.domain.tables.daos.LCountryDao;
import cn.vertxup.lbs.domain.tables.daos.LRegionDao;
import cn.vertxup.lbs.domain.tables.daos.LStateDao;
import cn.vertxup.lbs.domain.tables.pojos.LCity;
import cn.vertxup.lbs.domain.tables.pojos.LRegion;
import cn.vertxup.lbs.domain.tables.pojos.LState;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.lbs.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.commune.Envelop;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.function.Supplier;

@Queue
public class QueryActor {
    @Address(Addr.PickUp.COUNTRIES)
    public Future<JsonArray> queryCountries(final Envelop request) {
        return Ux.Jooq.on(LCountryDao.class)
                .findAllAsync()
                .compose(Ux::fnJArray);
    }

    @Address(Addr.PickUp.STATE_BY_COUNTRY)
    public Future<JsonArray> queryStates(final String countryId) {
        return Ux.Jooq.on(LStateDao.class)
                .fetchAsync("countryId", countryId)
                .compose(Ux::fnJArray);
    }

    @Address(Addr.PickUp.CITY_BY_STATE)
    public Future<JsonArray> queryCities(final String stateId) {
        return Ux.Jooq.on(LCityDao.class)
                .fetchAsync("stateId", stateId)
                .compose(Ux::fnJArray);
    }

    @Address(Addr.PickUp.REGION_BY_CITY)
    public Future<JsonArray> queryRegions(final String cityId) {
        return Ux.Jooq.on(LRegionDao.class)
                .fetchAsync("cityId", cityId)
                .compose(Ux::fnJArray);
    }

    @Address(Addr.PickUp.REGION_META)
    public Future<JsonObject> initRegion(final String id) {
        /*
         * Region -> City -> State -> Country
         */
        final JsonObject response = new JsonObject();
        return Ux.toFuture(id)
                /*
                 * Region Instance
                 */
                .compose(regionId -> this.combine(response, "regionId",
                        () -> regionId))
                .compose(regionId -> Ux.Jooq.on(LRegionDao.class)
                        .<LRegion>fetchOneAsync(KeField.KEY, regionId)
                )
                /*
                 * Region -> City
                 */
                .compose(region -> this.combine(response, "cityId",
                        region::getCityId))
                .compose(cityId -> Ux.Jooq.on(LCityDao.class)
                        .<LCity>fetchOneAsync(KeField.KEY, cityId)
                )
                /*
                 * City -> State
                 */
                .compose(city -> this.combine(response, "stateId",
                        city::getStateId))
                .compose(stateId -> Ux.Jooq.on(LStateDao.class)
                        .<LState>fetchOneAsync(KeField.KEY, stateId)
                )
                /*
                 * State -> Country
                 */
                .compose(state -> this.combine(response, "countryId",
                        state::getCountryId))
                .compose(countryId -> Ux.toFuture(response));
    }

    private Future<String> combine(
            final JsonObject data, final String field,
            final Supplier<String> valueFun
    ) {
        final String value = valueFun.get();
        if (Ut.notNil(value)) {
            data.put(field, value);
        }
        return Ux.toFuture(value);
    }
}
