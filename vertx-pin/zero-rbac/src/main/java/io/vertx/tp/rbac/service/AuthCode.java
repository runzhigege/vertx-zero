package io.vertx.tp.rbac.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

/*
 * Auth code implementation
 */
class AuthCode {
    private static final Annal LOGGER = Annal.get(AuthCode.class);
    /*
     * Initialize
     */
    private static final String STATE = "state";
    private static final ScConfig CONFIG = ScPin.getConfig();
    private static AuthCode INSTANCE;

    private AuthCode() {
    }

    static AuthCode create() {
        if (null == INSTANCE) {
            INSTANCE = new AuthCode();
        }
        return INSTANCE;
    }

    Future<JsonObject> authorize(final String clientId, final String state) {
        // Generate random authorization code
        final Integer codeLength = CONFIG.getCodeLength();
        final String authCode = Ut.randomString(codeLength);
        Sc.infoAuth(LOGGER, "Generated Authorization Code: {0}", authCode);
        // Whether existing state
        final JsonObject response = new JsonObject();
        if (Ut.notNil(state)) {
            response.put(STATE, state);
        }
        // Enable SharedClient to store authCode
        final String codePool = CONFIG.getCodePool();
        final Integer codeExpired = CONFIG.getCodeExpired();
        return Ux.Pool.on(codePool).put(clientId, authCode, codeExpired)
                .compose(item -> Uson.create(response)
                        .append("code", item.getValue())
                        .toFuture());
    }
}
