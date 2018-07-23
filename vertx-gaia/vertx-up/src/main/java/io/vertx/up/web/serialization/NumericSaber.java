package io.vertx.up.web.serialization;

import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;

import java.util.function.Function;

/**
 * Int, Long, Short
 */
public abstract class NumericSaber extends BaseSaber {

    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.getNull(() ->
                        Fn.getSemi(this.isValid(paramType), this.getLogger(),
                                () -> {
                                    this.verifyInput(!Ut.isInteger(literal), paramType, literal);
                                    return this.getFun().apply(literal);
                                }, Fn::nil),
                paramType, literal);
    }

    protected abstract boolean isValid(final Class<?> paramType);

    protected abstract <T> Function<String, T> getFun();
}
