package cn.vertxup.erp.service;

import cn.vertxup.erp.domain.tables.daos.EEmployeeDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.optic.business.ExUser;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Objects;

public class EmployeeService implements EmployeeStub {

    @Override
    public Future<JsonObject> createAsync(final JsonObject data) {
        return null;
    }

    @Override
    public Future<JsonObject> fetchAsync(final String key) {
        final ExUser user = Pocket.lookup(ExUser.class);
        return Ux.Jooq.on(EEmployeeDao.class).findByIdAsync(key)
                .compose(Ux::fnJObject)
                .compose(this::fetchRef);
    }

    @Override
    public Future<JsonObject> updateAsync(final String key, final JsonObject data) {
        return null;
    }

    @Override
    public Future<Boolean> deleteAsync(final String key) {
        return null;
    }

    private Future<JsonObject> fetchRef(final JsonObject input) {
        final ExUser user = Pocket.lookup(ExUser.class);
        if (Objects.isNull(user) || Ut.isNil(input)) {
            return Ux.toFuture(input);
        } else {
            final JsonObject filters = new JsonObject();
            filters.put(KeField.IDENTIFIER, "employee");
            filters.put(KeField.SIGMA, input.getString(KeField.SIGMA));
            filters.put(KeField.KEY, input.getString(KeField.KEY));
            return user.fetchRef(filters).compose(Ux.applyJNil(response ->
                    Ux.toFuture(input.put(KeField.USER_ID, response.getString(KeField.KEY)))));
        }
    }
}
