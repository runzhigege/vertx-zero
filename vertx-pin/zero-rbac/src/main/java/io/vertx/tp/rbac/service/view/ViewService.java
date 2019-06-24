package io.vertx.tp.rbac.service.view;

import cn.vertxup.domain.tables.daos.SViewDao;
import cn.vertxup.domain.tables.pojos.SView;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.cv.em.OwnerType;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;

import java.util.List;
import java.util.UUID;

public class ViewService implements ViewStub {

    private static final Annal LOGGER = Annal.get(ViewService.class);

    @Override
    public Future<SView> fetchMatrix(final String userId, final String resourceId, final String view) {
        /* Find user matrix */
        final JsonObject filters = this.toFilters(resourceId, view);
        filters.put("owner", userId);
        filters.put("ownerType", OwnerType.USER.name());
        Sc.infoResource(LOGGER, AuthMsg.VIEW_PROCESS, "fetch", filters.encode());
        return Ux.Jooq.on(SViewDao.class)
                .fetchOneAsync(new JsonObject().put("criteria", filters));
    }

    @Override
    public Future<SView> saveMatrix(final String userId, final String resourceId,
                                    final String view, final String sigma, final JsonArray projection) {
        /* Find user matrix */
        final JsonObject filters = this.toFilters(resourceId, view);
        filters.put("owner", userId);
        filters.put("ownerType", OwnerType.USER.name());
        /* SView projection */
        Sc.infoResource(LOGGER, AuthMsg.VIEW_PROCESS, "save", filters.encode());
        final SView myView = this.toView(filters, sigma, projection);
        return Ux.Jooq.on(SViewDao.class)
                .upsertAsync(filters, myView);
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

    private SView toView(final JsonObject filters, final String sigma, final JsonArray projection) {
        final JsonObject data = filters.copy()
                .put(Inquiry.KEY_PROJECTION, projection.encode());
        data.put(KeField.KEY, UUID.randomUUID().toString());
        data.put(KeField.ACTIVE, Boolean.TRUE);
        data.put("rows", new JsonObject().encode());
        data.put(Inquiry.KEY_CRITERIA, new JsonObject().encode());
        data.put(KeField.SIGMA, sigma);
        return Ut.deserialize(data, SView.class);
    }
}
