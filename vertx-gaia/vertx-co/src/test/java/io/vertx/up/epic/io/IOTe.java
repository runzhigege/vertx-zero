package io.vertx.up.epic.io;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import io.vertx.zero.exception.heart.EmptyStreamException;
import org.junit.Test;

public class IOTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testProp(final TestContext context) {
        context.assertNotNull(IO.getProp("in.properties"));
    }
}
