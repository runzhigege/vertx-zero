package io.vertx.tp.rbac.service.jwt;

import cn.vertxup.domain.tables.daos.OAccessTokenDao;
import cn.vertxup.domain.tables.pojos.OAccessToken;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Ux;

/*
 * Jwt Token Service for:
 * 1) Stored token information into jwt token
 * 2) Verify token based on stored access_token in database.
 */
public class JwtService implements JwtStub {
    @Override
    public Future<JsonObject> store(final String userKey, final JsonObject data) {
        /*
         * Jwt Token response building
         */
        final JsonObject response = Sc.jwtToken(data);
        /*
         * Jwt OAccessToken
         */
        final OAccessToken accessToken = Sc.jwtToken(data, userKey);
        return Ux.Jooq.on(OAccessTokenDao.class)
                .insertAsync(accessToken)
                .compose(item -> Ux.toFuture(response));
    }
}
