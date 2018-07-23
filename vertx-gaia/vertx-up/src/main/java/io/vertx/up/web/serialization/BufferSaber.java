package io.vertx.up.web.serialization;

import io.vertx.core.buffer.Buffer;
import io.vertx.up.epic.fn.Fn;
import io.vertx.zero.eon.Values;

/**
 * Buffer
 */
public class BufferSaber extends BaseSaber {
    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.getNull(() ->
                        Fn.getSemi(Buffer.class == paramType, this.getLogger(),
                                () -> {
                                    final Buffer buffer = Buffer.buffer();
                                    buffer.appendBytes(literal.getBytes(Values.CHARSET));
                                    return buffer;
                                }, Buffer::buffer),
                paramType, literal);
    }
}
