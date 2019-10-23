package cn.vertxup.ambient.service;

import cn.vertxup.ambient.domain.tables.daos.XTodoDao;
import cn.vertxup.ambient.domain.tables.pojos.XTodo;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.init.AtPin;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Objects;

public class TodoService implements TodoStub {
    @Override
    public Future<JsonObject> createTodo(final String type, final JsonObject data) {
        /*
         * Get by type
         */
        final JsonObject defaultTodo = AtPin.getTodo(type);
        final JsonObject inputData = data.copy();
        if (Objects.nonNull(defaultTodo)) {
            /*
             * Expression for defaultTodo
             */
            final JsonObject params = data.copy();
            final String name = Ut.fromExpression(defaultTodo.getString(KeField.NAME), params);
            final String code = Ut.fromExpression(defaultTodo.getString(KeField.CODE), params);
            final String url = Ut.fromExpression(defaultTodo.getString("todoUrl"), params);
            inputData.mergeIn(defaultTodo);
            inputData.put(KeField.NAME, name);
            inputData.put(KeField.CODE, code);
            inputData.put("todoUrl", url);
        }
        final XTodo todo = Ut.deserialize(inputData, XTodo.class);
        return Ux.Jooq.on(XTodoDao.class)
                .insertAsync(todo)
                .compose(Ux::fnJObject);
    }

    @Override
    public Future<JsonArray> fetchTodos(final String sigma, final String type, final JsonArray statues) {
        final JsonObject filters = new JsonObject();
        filters.put("sigma", sigma);
        if (Objects.nonNull(type)) {
            filters.put("type", type);
        }
        filters.put("status,i", statues);
        return Ux.Jooq.on(XTodoDao.class)
                .fetchAndAsync(filters)
                .compose(Ux::fnJArray);
    }
}
