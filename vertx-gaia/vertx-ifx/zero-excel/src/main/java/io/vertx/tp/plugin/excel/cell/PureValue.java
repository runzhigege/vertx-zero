package io.vertx.tp.plugin.excel.cell;

public class PureValue implements ExValue {

    private static ExValue INSTANCE = null;

    private PureValue() {

    }

    static ExValue create() {
        if (null == INSTANCE) {
            INSTANCE = new PureValue();
        }
        return INSTANCE;
    }

    @Override
    @SuppressWarnings("all")
    public Object to(Object value) {
        return value;
    }
}
