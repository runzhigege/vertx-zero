package io.vertx.up.epic;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import org.junit.Test;

public class IOTc extends StoreBase {
    @Test
    public void testProp(final TestContext context) {
        context.assertNotNull(IO.getProp(this.ioFile("in.properties")));
    }

    @Test
    public void testYaml(final TestContext context) {
        context.assertNotNull(IO.getYaml(this.ioFile("test.yaml")));
    }
}
