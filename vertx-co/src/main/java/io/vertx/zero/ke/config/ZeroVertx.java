package io.vertx.zero.ke.config;

import com.vie.hoc.HBool;
import com.vie.hoc.HFail;
import com.vie.util.io.IO;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.ZeroNode;

/**
 * @author lang
 */
public class ZeroVertx implements ZeroNode<JsonObject> {

    @Override
    public JsonObject read() {
        // Not null because execNil
        return HFail.execDft(() -> {
            final JsonObject raw = IO.getYaml(Path.KE_VERTX);
            return HBool.execTrue(raw.containsKey(Keys.ZERO),
                    () -> raw.getJsonObject(Keys.ZERO));
        }, new JsonObject());
    }
}
