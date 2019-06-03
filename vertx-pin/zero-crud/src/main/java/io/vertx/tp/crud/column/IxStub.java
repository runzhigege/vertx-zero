package io.vertx.tp.crud.column;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.Objects;

/*
 * Column
 */
public class IxStub {

    private static final String COLUMN = "column";
    private static final Node<JsonObject> visitor = Ut.singleton(ZeroUniform.class);

    public static ColStub getStub() {
        final JsonObject config = visitor.read();
        final JsonObject columnConfig = config.getJsonObject(COLUMN);
        /* Column Class */
        final String columnClsName = columnConfig.getString("component");
        return Fn.getJvm(() -> {
            Class<?> columnCls = Ut.clazz(columnClsName);
            if (Objects.nonNull(columnCls)) {
                columnCls = ColService.class;
            }
            return Ut.instance(columnCls);
        }, columnClsName);
    }
}
