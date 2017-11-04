package com.vie.util;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import top.StoreBase;

public class StoreTc extends StoreBase {

    @Test
    public void testJson(final TestContext context) {
        execJObject("test.json", config -> {
            context.assertNotNull(config);
        });
    }
}
