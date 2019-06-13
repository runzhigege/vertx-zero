package io.vertx.tp.rbac.service.authorization;

import cn.vertxup.domain.tables.daos.SActionDao;
import cn.vertxup.domain.tables.daos.SResourceDao;
import cn.vertxup.domain.tables.pojos.SAction;
import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.vertx.zero.eon.Strings;

public class ActionService implements ActionStub {

    @Override
    public Future<SAction> fetchAction(final String normalizedUri,
                                       final HttpMethod method) {
        final JsonObject actionFilters = new JsonObject();
        actionFilters.put(Strings.EMPTY, Boolean.TRUE);
        actionFilters.put("uri", normalizedUri);
        actionFilters.put("method", method.name());
        return Ux.Jooq.on(SActionDao.class)
                .fetchOneAndAsync(actionFilters);
    }

    @Override
    public Future<SResource> fetchResource(final String key) {
        return Ux.Jooq.on(SResourceDao.class)
                .findByIdAsync(key);
    }
}
