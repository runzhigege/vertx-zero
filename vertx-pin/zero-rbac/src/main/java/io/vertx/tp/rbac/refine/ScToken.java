package io.vertx.tp.rbac.refine;

import cn.vertxup.domain.tables.pojos.OAccessToken;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

class ScToken {
    private static final Annal LOGGER = Annal.get(ScToken.class);
    private static final ScConfig CONFIG = ScPin.getConfig();

    static JsonObject jwtToken(final JsonObject data) {
        /* Token Data Extract */
        final JsonObject tokenData = Uson.create(data.copy())
                .remove("role", "group").to();
        Sc.infoAuth(LOGGER, AuthMsg.TOKEN_JWT, tokenData.encode());
        /* Token */
        final String token = Ux.Jwt.token(tokenData);

        /* Response */
        final JsonObject response = new JsonObject();
        response.put(AuthKey.ACCESS_TOKEN, token);

        /* Refresh Token */
        final String refreshToken = Ux.Jwt.token(response.copy());
        response.put(AuthKey.REFRESH_TOKEN, refreshToken);

        /* Token Expired */
        final Long iat = new Date().getTime() + CONFIG.getTokenExpired();
        response.put(AuthKey.IAT, iat);
        return response;
    }

    static OAccessToken jwtToken(final JsonObject jwt, final String userKey) {
        /* Token byte[] */
        final byte[] token = jwt.getString(AuthKey.ACCESS_TOKEN).getBytes(Values.CHARSET);
        /* Refresh Token byte[] */
        final byte[] refreshToken = jwt.getString(AuthKey.REFRESH_TOKEN).getBytes(Values.CHARSET);
        final Long iat = jwt.getLong(AuthKey.IAT);
        return new OAccessToken()
                .setKey(UUID.randomUUID().toString())
                /* Created Auditor */
                .setCreatedBy(userKey)
                .setCreatedAt(LocalDateTime.now())
                /* Token Info */
                .setToken(token)
                .setRefreshToken(refreshToken)
                .setExpiredTime(iat)
                /* Active */
                .setActive(Boolean.TRUE);
    }
}
