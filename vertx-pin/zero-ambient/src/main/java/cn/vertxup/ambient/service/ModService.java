package cn.vertxup.ambient.service;

import cn.vertxup.ambient.domain.tables.daos.XModuleDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.optic.business.ExModel;
import io.vertx.up.exception.WebException;
import io.vertx.up.unity.Ux;

public class ModService implements ModStub {
    @Override
    public Future<JsonObject> fetchModule(final String appId, final String entry) {
        final JsonObject filters = new JsonObject()
                .put("", Boolean.TRUE)
                .put("entry", entry)
                .put("appId", appId);
        return Ux.Jooq.on(XModuleDao.class)
                .fetchOneAsync(filters)
                .compose(Ux::fnJObject)
                /* Metadata field usage */
                .compose(Ke.mount(KeField.METADATA));
    }

    @Override
    public Future<JsonArray> fetchModels(final String sigma) {
        try {
            final ExModel model = Pocket.lookup(ExModel.class, this.getClass());
            return model.fetchAsync(sigma);
        } catch (final WebException ex) {
            return Ux.future(new JsonArray());
        }
    }
}
