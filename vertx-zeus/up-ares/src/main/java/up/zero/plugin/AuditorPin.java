package up.zero.plugin;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.up.atom.Envelop;
import io.vertx.up.plugin.auditor.ZeroAuditor;

public class AuditorPin implements ZeroAuditor {

    @Override
    public void audit(final Envelop envelop) {
        /* Get User */
        final User user = envelop.user();
        if (null != user) {
            /* Extract User Information */
            final JsonObject userData = user.principal();
            /* Get user id */
            final String userId = userData.getString("user");
            envelop.setValue(1, "userId", userId);
        }
    }
}
