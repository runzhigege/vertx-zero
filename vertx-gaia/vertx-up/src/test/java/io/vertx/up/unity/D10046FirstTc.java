package io.vertx.up.unity;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.Envelop;
import io.vertx.quiz.EpicBase;
import org.junit.Assert;
import org.junit.Test;

public class D10046FirstTc extends EpicBase {
    @Test
    public void testInput() {
        final JsonObject input = this.ioJObject("d10046.json");
        // Uson usage
        final JsonObject ret = Uson.create(input)
                .convert("password", "updated").to();
        System.err.println(ret.encodePrettily());
        Assert.assertEquals("111111", ret.getString("updated"));
    }

    @Test
    public void testInputArr() {
        final JsonArray input = this.ioJArray("d10046-arr.json");
        // Uson usage
        final JsonArray ret = Uarr.create(input)
                .convert("password", "updated").to();
        System.err.println(ret.encodePrettily());
        Assert.assertEquals("111111", ret.getJsonObject(0).getString("updated"));
        Assert.assertEquals("222222", ret.getJsonObject(1).getString("updated"));
    }

    @Test
    public void testUx() {
        final JsonObject input = this.ioJObject("d10046.json");
        final Envelop envelop = Ux.to(input);
        Assert.assertNotNull(envelop.data());
        System.err.println(envelop.data(JsonObject.class));
    }
}
