package io.vertx.tp.plugin.excel.cell;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Strings;
import io.vertx.up.util.Ut;

/*
 * Fix issue of excel length: 32767 characters
 */
@SuppressWarnings("all")
public class JsonValue implements ExValue {
    @Override
    public String to(final Object value) {
        final String[] pathArr = value.toString().split(Strings.COLON);
        String literal = value.toString();
        if (2 == pathArr.length) {
            final String path = pathArr[1];
            if (Ut.notNil(path)) {
                final JsonObject content = Ut.ioJObject(path.trim());
                if (Ut.notNil(content)) {
                    literal = content.encodePrettily();
                }
            }
        }
        return literal;
    }
}
