package io.vertx.up.web.serialization;

import io.vertx.up.epic.fn.Fn;
import io.vertx.up.exception._400ParameterFromStringException;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroSerializer;

public abstract class BaseSaber implements Saber {

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }

    protected void verifyInput(final boolean condition,
                               final Class<?> paramType,
                               final String literal) {
        Fn.outUp(condition,
                this.getLogger(), _400ParameterFromStringException.class,
                ZeroSerializer.class, paramType, literal);
    }

    @Override
    public <T> Object from(final T input) {
        // Default direct
        return input;
    }

    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        // Default direct
        return literal;
    }
}
