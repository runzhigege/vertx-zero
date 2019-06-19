package io.vertx.tp.ke;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.extension.ui.ColumnStub;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.Objects;

public class KePin {
    /* VerticalStub */
    private static final String COLUMN = "column";
    private static final String JOIN = "join";

    private static final Node<JsonObject> visitor = Ut.singleton(ZeroUniform.class);

    private static Class<?> getComponent(final String key) {
        final JsonObject uniform = visitor.read();
        final JsonObject config = uniform.getJsonObject("kern-class");
        /* Component Class */
        final String componentClsName = config.getString(key);
        return Fn.getJvm(() -> Ut.clazz(componentClsName), componentClsName);
    }

    public static ColumnStub getVertical() {
        final Class<?> columnCls = getComponent(COLUMN);
        return Objects.isNull(columnCls) ? null : Ut.singleton(columnCls);
    }

    public static JoinStub getJoin() {
        final Class<?> joinCls = getComponent(JOIN);
        return Objects.isNull(joinCls) ? null : Ut.singleton(joinCls);
    }
}
