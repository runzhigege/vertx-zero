package io.vertx.tp.rbac.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._401CodeExpiredException;
import io.vertx.tp.error._401CodeWrongException;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

/*
 * Auth code implementation
 */
class ScCode {
    private static final Annal LOGGER = Annal.get(ScCode.class);
    /*
     * Initialize
     */
    private static final String STATE = "state";
    private static final ScConfig CONFIG = ScPin.getConfig();
    private static ScCode INSTANCE;

    private ScCode() {
    }

    static ScCode create() {
        if (null == INSTANCE) {
            INSTANCE = new ScCode();
        }
        return INSTANCE;
    }

    /*
     * Verify authorization code
     * {
     *      "code":"xxx",
     *      "client_id":"xxx"
     * }
     */
    @SuppressWarnings("all")
    Future<String> verify(final JsonObject params) {
        final String code = params.getString("code");
        final String client_id = params.getString("client_id");
        Sc.infoAuth(LOGGER, "Input data when verification: client_id = {0}, code = {1}", client_id, code);

        // Get the config
        final String codePool = CONFIG.getCodePool();
        return Ux.Pool.on(codePool).get(client_id).compose(item -> Fn.match(() ->
                        Fn.fork(() -> Ux.toFuture(client_id)),
                // Authorization Code Expired
                Fn.branch(null == item, () -> Ux.thenError(_401CodeExpiredException.class, this.getClass(), client_id, code)),
                // Wrong
                Fn.branch(null != item && !code.equals(item), () -> Ux.thenError(_401CodeWrongException.class, this.getClass(), client_id))
        ));
    }

    /*
     * Get authorization code and generate the code here.
     */
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
