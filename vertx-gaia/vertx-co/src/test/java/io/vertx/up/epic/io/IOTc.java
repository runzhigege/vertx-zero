package io.vertx.up.epic.io;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import org.junit.Test;

public class IOTc extends StoreBase {
    @Test
    public void testProp(final TestContext context) {
        context.assertNotNull(IO.getProp(this.getFile("in.properties")));
    }

    @Test
    public void testYaml(final TestContext context) {
        context.assertNotNull(IO.getYaml(this.getFile("test.yaml")));
    }
}
