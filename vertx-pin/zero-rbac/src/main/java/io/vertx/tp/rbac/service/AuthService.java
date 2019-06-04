package io.vertx.tp.rbac.service;

import cn.vertxup.domain.tables.daos.OUserDao;
import cn.vertxup.domain.tables.pojos.OUser;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._401CodeGenerationException;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;
import io.zero.epic.fn.Fn;

public class AuthService implements AuthStub {

    private static final Annal LOGGER = Annal.get(AuthService.class);
    private transient final ScUser scUser = ScUser.create();
    private transient final ScCode scCode = ScCode.create();

    @Override
    @SuppressWarnings("all")
    public Future<JsonObject> authorize(final JsonObject filters, final String state) {
        Sc.infoAuth(LOGGER, "Authorization Code Filters: {0}", filters.encode());
        return Ux.Jooq.on(OUserDao.class).<OUser>fetchOneAndAsync(filters).compose(item -> Fn.match(

                // Provide correct parameters, OUser record existing.
                () -> Fn.fork(() -> scCode.authorize(item.getClientId(), state)),

                // Could not identify OUser record, error throw.
                Fn.branch(null == item, () ->
                        Ux.thenError(_401CodeGenerationException.class, this.getClass(),
                                filters.getString("clientId"), filters.getString("clientSecret")))
        ));
    }

    @Override
    public Future<JsonObject> token(final JsonObject filters, final String state) {
        return null;
    }

    @Override
    public Future<JsonObject> login(final String username, final String password) {
        return this.scUser.login(username, password);
    }
}
