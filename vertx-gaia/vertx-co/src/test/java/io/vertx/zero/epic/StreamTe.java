package io.vertx.zero.epic;

import io.vertx.ext.unit.TestContext;
import io.vertx.zero.exception.heart.EmptyStreamException;
import io.vertx.quiz.StoreBase;
import org.junit.Test;

public class StreamTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testRead(final TestContext context) {
        context.assertNotNull(Stream.read(this.ioFile("ini.txt")));
    }
}
