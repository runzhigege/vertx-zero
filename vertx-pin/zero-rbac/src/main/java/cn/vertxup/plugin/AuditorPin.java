package cn.vertxup.plugin;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.commune.Envelop;
import io.vertx.up.extension.PlugAuditor;
import io.vertx.up.unity.Ux;

public class AuditorPin implements PlugAuditor {

    @Override
    public Future<Envelop> audit(final RoutingContext context,
                                 final Envelop envelop) {
        /* Get User */
        final User user = envelop.user();
        if (null != user) {
            /* Extract User Information */
            final JsonObject userData = user.principal();
            /* Get user id */
            final String userId = userData.getString("user");
            envelop.setValue(1, "userId", userId);
        }
        return Ux.toFuture(envelop);
    }
}
