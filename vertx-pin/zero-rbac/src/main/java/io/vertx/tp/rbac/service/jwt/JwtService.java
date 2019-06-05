package io.vertx.tp.rbac.service.jwt;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * Jwt Token Service for:
 * 1) Stored token information into jwt token
 * 2) Verify token based on stored access_token in database.
 */
public class JwtService implements JwtStub {
    @Override
    public Future<JsonObject> store(final String userKey, final JsonObject data) {

        return Future.succeededFuture(data);
    }
}
