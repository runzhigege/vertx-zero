package io.vertx.tp.rbac.service.view;

import cn.vertxup.domain.tables.daos.SViewDao;
import cn.vertxup.domain.tables.pojos.SView;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.em.OwnerType;
import io.vertx.up.aiki.Ux;
import io.vertx.zero.eon.Strings;

import java.util.List;

public class ViewService implements ViewStub {

    @Override
    public Future<SView> fetchMatrix(final String userId, final String resourceId, final String view) {
        /* Find user matrix */
        final JsonObject filters = this.toFilters(resourceId, view);
        filters.put("owner", userId);
        filters.put("ownerType", OwnerType.USER.name());
        return Ux.Jooq.on(SViewDao.class)
                .fetchOneAsync(new JsonObject().put("criteria", filters));
    }

    @Override
    public Future<List<SView>> fetchMatrix(final JsonArray roleIds, final String resourceId, final String view) {
        /* Find user matrix */
        final JsonObject filters = this.toFilters(resourceId, view);
        filters.put("owner,i", roleIds);
        filters.put("ownerType", OwnerType.ROLE.name());
        return Ux.Jooq.on(SViewDao.class)
                .fetchAndAsync(new JsonObject().put("criteria", filters));
    }

    private JsonObject toFilters(final String resourceId, final String view) {
        final JsonObject filters = new JsonObject();
        filters.put(Strings.EMPTY, Boolean.TRUE);
        filters.put("resourceId", resourceId);
        filters.put("name", view);
        return filters;
    }
}
