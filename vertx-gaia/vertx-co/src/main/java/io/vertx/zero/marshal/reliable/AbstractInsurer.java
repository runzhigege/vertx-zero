package io.vertx.zero.marshal.reliable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.demon.JObjectElementException;

/**
 * @author lang
 */
public abstract class AbstractInsurer implements Insurer {

    @Override
    public void flumen(final JsonArray array,
                       final JsonObject elementRule)
            throws ZeroException {
        Fn.shuntZero(() -> {
            // 1. Verify the element must be json object for each
            final int size = array.size();
            for (int idx = Values.IDX; idx < size; idx++) {
                final Object value = array.getValue(idx);
                // 2. Call check method to confirm JsonObject
                Fn.outZero(!Ut.isJObject(value), this.getLogger(),
                        JObjectElementException.class,
                        this.getClass(), idx, value);

                final JsonObject item = (JsonObject) value;
                // 3. Apply the rule to each object.
                this.flumen(item, elementRule);
            }
        }, array, elementRule);
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
