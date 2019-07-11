package io.vertx.up.atom.query;

import io.vertx.core.json.JsonObject;
import io.vertx.up.exception._500QueryMetaNullException;
import io.vertx.up.log.Annal;
import io.vertx.up.epic.Ut;
import io.vertx.up.fn.Fn;

import java.io.Serializable;

/**
 * Criteria for condition set, the connector is and
 * Advanced criteria will use tree mode, the flat mode is "AND"
 */
public class Criteria implements Serializable {

    private static final Annal LOGGER = Annal.get(Criteria.class);
    private final Inquiry.Mode mode;
    private transient QLinear linear;
    private transient QTree tree;

    private Criteria(final JsonObject data) {
        Fn.outWeb(null == data, LOGGER,
                _500QueryMetaNullException.class, this.getClass());
        this.mode = this.parseMode(data);
        if (Inquiry.Mode.LINEAR == this.mode) {
            this.linear = QLinear.create(data);
        } else {
            this.tree = QTree.create(data);
        }
    }

    public static Criteria create(final JsonObject data) {
        return new Criteria(data);
    }

    private Inquiry.Mode parseMode(final JsonObject data) {
        Inquiry.Mode mode = Inquiry.Mode.LINEAR;
        for (final String field : data.fieldNames()) {
            if (Ut.isJObject(data.getValue(field))) {
                mode = Inquiry.Mode.TREE;
                break;
            }
        }
        return mode;
    }

    public boolean isValid() {
        if (Inquiry.Mode.LINEAR == this.mode) {
            return this.linear.isValid();
        } else {
            return this.tree.isValid();
        }
    }

    public Inquiry.Mode getMode() {
        return this.mode;
    }

    public Criteria add(final String field, final Object value) {
        if (Inquiry.Mode.LINEAR == this.mode) {
            this.linear.add(field, value);
        }
        return this;
    }

    public JsonObject toJson() {
        if (Inquiry.Mode.LINEAR == this.mode) {
            return this.linear.toJson();
        } else {
            return this.tree.toJson();
        }
    }
}
