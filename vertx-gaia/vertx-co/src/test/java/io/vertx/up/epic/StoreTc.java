package io.vertx.up.epic;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import io.vertx.zero.log.Log;
import org.junit.Test;

public class StoreTc extends StoreBase {

    @Test
    public void testJson(final TestContext context) {
        this.execJObject("test.json", config -> {
            Log.info(this.getLogger(), config.result().encode());
            context.assertTrue(config.succeeded());
        });
    }

    @Test
    public void testProp(final TestContext context) {
        this.execProp("test.properties", config -> {
            Log.info(this.getLogger(), config.result().encode());
            context.assertTrue(config.succeeded());
        });
    }
}
