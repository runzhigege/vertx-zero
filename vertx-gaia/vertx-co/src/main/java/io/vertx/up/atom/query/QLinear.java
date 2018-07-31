package io.vertx.up.atom.query;

import io.vertx.core.json.JsonObject;
import io.vertx.up.exception._400OpUnsupportException;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Strings;
import io.zero.epic.container.KeyPair;
import io.zero.epic.fn.Fn;

import java.util.ArrayList;
import java.util.List;

public class QLinear {

    private static final Annal LOGGER = Annal.get(QLinear.class);
    private final List<KeyPair<String, KeyPair<String, Object>>> conditions = new ArrayList<>();
    private final transient JsonObject raw = new JsonObject();

    private QLinear(final JsonObject data) {
        this.raw.mergeIn(data.copy());
        for (final String field : data.fieldNames()) {
            // Add
            this.add(field, data.getValue(field));
        }
    }

    public static QLinear create(final JsonObject data) {
        return new QLinear(data);
    }

    public List<KeyPair<String, KeyPair<String, Object>>> getConditions() {
        return this.conditions;
    }

    public boolean isValid() {
        return !this.conditions.isEmpty();
    }

    public QLinear add(final String field, final Object value) {
        // Field add
        final String filterField;
        final String op;
        if (field.contains(Strings.COMMA)) {
            filterField = field.split(Strings.COMMA)[0];
            op = field.split(Strings.COMMA)[1];
        } else {
            filterField = field;
            op = Inquiry.Op.EQ;
        }
        Fn.outWeb(!Inquiry.Op.VALUES.contains(op), LOGGER,
                _400OpUnsupportException.class, this.getClass(), op);
        final KeyPair<String, Object> condition = KeyPair.create(op, value);
        final KeyPair<String, KeyPair<String, Object>> item = KeyPair.create(filterField, condition);
        // At the same time.
        this.conditions.add(item);
        return this;
    }

    public JsonObject toJson() {
        return this.raw;
    }
}
