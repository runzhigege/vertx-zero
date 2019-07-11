package io.vertx.zero.epic;

import io.vertx.ext.unit.TestContext;
import io.vertx.zero.exception.heart.EmptyStreamException;
import io.vertx.quiz.StoreBase;
import org.junit.Test;

public class IOTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testProp(final TestContext context) {
        context.assertNotNull(IO.getProp("in.properties"));
    }
}
