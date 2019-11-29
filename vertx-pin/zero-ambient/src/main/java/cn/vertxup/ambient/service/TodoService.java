package cn.vertxup.ambient.service;

import cn.vertxup.ambient.domain.tables.daos.XTodoDao;
import cn.vertxup.ambient.domain.tables.pojos.XTodo;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.cv.AtMsg;
import io.vertx.tp.ambient.cv.em.TodoStatus;
import io.vertx.tp.ambient.init.AtPin;
import io.vertx.tp.ambient.refine.At;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.optic.business.ExTodo;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.time.LocalDateTime;
import java.util.Objects;

public class TodoService implements TodoStub {
    private static final Annal LOGGER = Annal.get(TodoService.class);

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
        return Ux.Jooq.on(XTodoDao.class).fetchAndAsync(filters).compose(Ux::fnJArray);
    }

    @Override
    public Future<JsonArray> fetchTodos(final String sigma, final JsonArray types, final JsonArray statues) {
        final JsonObject filters = new JsonObject();
        filters.put("sigma", sigma);
        if (Objects.nonNull(types)) {
            filters.put("type,i", types);
        }
        filters.put("status,i", statues);
        return Ux.Jooq.on(XTodoDao.class).fetchAndAsync(filters).compose(Ux::fnJArray);
    }

    @Override
    public Future<JsonObject> updateStatus(final String key, final JsonObject params) {
        return Ux.Jooq.on(XTodoDao.class)
                .<XTodo>findByIdAsync(key)
                .compose(Ux::fnJObject)
                .compose(Ut.applyJNil((todoJson) -> {
                    /*
                     * Update status of XTodo
                     */
                    final XTodo todo = Ut.deserialize(todoJson, XTodo.class);
                    {
                        /*
                         * XTodo Auditor setting
                         */
                        if (params.containsKey(KeField.USER_ID)) {
                            todo.setUpdatedBy(params.getString(KeField.USER_ID));
                            todo.setUpdatedAt(LocalDateTime.now());
                        }
                        /*
                         * Status
                         */
                        if (params.containsKey(KeField.STATUS)) {
                            final String status = params.getString(KeField.STATUS);
                            todo.setStatus(status);
                            if (TodoStatus.FINISHED.name().equals(status)) {
                                /*
                                 * It means that XTodo has been updated by user
                                 */
                                todo.setFinishedBy(todo.getUpdatedBy());
                            }
                        }
                    }
                    return Ux.Jooq.on(XTodoDao.class)
                            .<XTodo>updateAsync(todo)
                            .compose(Ux::fnJObject);
                }));
    }

    @Override
    public Future<JsonObject> fetchTodo(final String key) {
        return Ux.Jooq.on(XTodoDao.class)
                .<XTodo>findByIdAsync(key)
                .compose(Ux::fnJObject)
                .compose(Ut.applyNil(JsonObject::new, (todo) ->
                        Ke.channel(ExTodo.class, () -> todo, channel -> {
                            At.infoInit(LOGGER, AtMsg.CHANNEL_TODO, channel.getClass().getName());
                            /*
                             * X_TODO channel and data merged.
                             */
                            final JsonObject params = Ut.elementSubset(todo,
                                    KeField.MODEL_ID, KeField.MODEL_CATEGORY, KeField.MODEL_KEY, KeField.SIGMA);
                            return channel.fetchAsync(key, params)
                                    .compose(Ut.applyMerge(todo));
                        })
                ));
    }
}
