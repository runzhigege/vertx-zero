package io.vertx.domain;

import cn.vertxup.domain.tables.pojos.SUser;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import org.junit.Test;

public class MobileDerTc {

    @Test
    public void testUser() {
        final SUser user = new SUser();
        user.setMobile("15922611447");
        /* Number */
        final JsonObject object = Ux.toJson(user);
        System.err.println(object.encodePrettily());
    }
}
