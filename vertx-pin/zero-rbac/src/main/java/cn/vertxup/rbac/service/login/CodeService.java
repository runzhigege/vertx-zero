package cn.vertxup.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._401CodeExpiredException;
import io.vertx.tp.error._401CodeWrongException;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;
import io.zero.epic.fn.Fn;

public class CodeService implements CodeStub {

    @Override
    public Future<JsonObject> authorize(final String clientId) {
        // Generate random authorization code
        final String authCode = Sc.generateCode();

        // Whether existing state
        final JsonObject response = new JsonObject();
        // Enable SharedClient to store authCode
        return Sc.cacheCode(clientId, authCode)
                .compose(item -> Uson.create(response)
                        .append(AuthKey.AUTH_CODE, item)
                        .toFuture());
    }

    @Override
    @SuppressWarnings("all")
    public Future<String> verify(final String clientId, final String code) {
        // Shared
        return Sc.cacheCode(clientId).compose(item -> Fn.match(
                // Successfully
                () -> Fn.fork(() -> Ux.toFuture(clientId)),
                // Authorization Code Expired
                Fn.branch(null == item, () -> Ux.thenError(_401CodeExpiredException.class, this.getClass(), clientId, code)),
                // Wrong
                Fn.branch(null != item && !code.equals(item), () -> Ux.thenError(_401CodeWrongException.class, this.getClass(), code))
        ));
    }
}
