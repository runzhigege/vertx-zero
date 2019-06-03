package io.vertx.tp.kern;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.Objects;

public class ColPin {

    private static final String COLUMN = "column";
    private static final Node<JsonObject> visitor = Ut.singleton(ZeroUniform.class);

    public static ColStub getStub() {
        final JsonObject config = visitor.read();
        final JsonObject columnConfig = config.getJsonObject(COLUMN);
        /* Column Class */
        final String columnClsName = columnConfig.getString("component");
        return Fn.getJvm(() -> {
            final Class<?> columnCls = Ut.clazz(columnClsName);
            if (Objects.nonNull(columnCls)) {
                return Ut.instance(columnCls);
            } else {
                return null;
            }
        }, columnClsName);
    }
}
