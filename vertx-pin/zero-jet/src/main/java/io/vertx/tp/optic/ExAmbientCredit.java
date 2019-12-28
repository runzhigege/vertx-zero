package io.vertx.tp.optic;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.atom.JtApp;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.up.unity.Ux;

import java.util.Objects;

public class ExAmbientCredit implements Credential {
    @Override
    public Future<JsonObject> fetchAsync(final String sigma) {
        final JtApp app = Ambient.getApp(sigma);
        final JsonObject credential = new JsonObject();
        if (Objects.nonNull(app)) {
            credential.put(KeField.SIGMA, sigma);
            credential.put(KeField.REALM, app.getName());
            credential.put(KeField.GRANT_TYPE, "authorization_code");
        }
        return Ux.future(credential);
    }
}
