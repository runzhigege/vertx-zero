package cn.vertxup.erp.service;

import cn.vertxup.erp.domain.tables.daos.EEmployeeDao;
import cn.vertxup.erp.domain.tables.pojos.EEmployee;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.optic.business.ExSerial;
import io.vertx.tp.optic.business.ExUser;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.function.BiFunction;

public class EmployeeService implements EmployeeStub {

    @Override
    public Future<JsonObject> createAsync(final JsonObject data) {
        final EEmployee employee = Ut.deserialize(data, EEmployee.class);
        if (Ut.isNil(employee.getWorkNumber())) {
            return Ke.onTunnelAsync(ExSerial.class,
                    () -> this.insertAsync(employee, data),
                    serial -> serial.serial(data.getString(KeField.SIGMA), "NUM.EMPLOYEE").compose(workNum -> {
                        employee.setWorkNumber(workNum);
                        return this.insertAsync(employee, data);
                    }));
        } else {
            return this.insertAsync(employee, data);
        }
    }

    private Future<JsonObject> insertAsync(final EEmployee employee, final JsonObject data) {
        return Ux.Jooq.on(EEmployeeDao.class).insertAsync(employee)
                .compose(Ux::fnJObject)
                .compose(inserted -> {
                    /*
                     * If data contains `userId`, it means that current employee will relate to
                     * an account
                     */
                    if (data.containsKey(USER_ID)) {
                        /*
                         * Create new relation here.
                         */
                        final String key = data.getString(USER_ID);
                        return this.updateRef(key, inserted);
                    } else {
                        return Ux.future(data);
                    }
                });
    }

    @Override
    public Future<JsonObject> fetchAsync(final String key) {
        return Ux.Jooq.on(EEmployeeDao.class).findByIdAsync(key)
                .compose(Ux::fnJObject)
                .compose(this::fetchRef);
    }

    @Override
    public Future<JsonObject> updateAsync(final String key, final JsonObject data) {
        return this.fetchAsync(key)
                .compose(Ut.applyNil(JsonObject::new, original -> {
                    final String userId = original.getString(USER_ID);
                    final String current = data.getString(USER_ID);
                    if (Ut.isNil(userId) && Ut.isNil(current)) {
                        /*
                         * Old null, new null
                         * Relation keep
                         */
                        return this.updateEmployee(key, data);
                    } else if (Ut.isNil(userId) && Ut.notNil(current)) {
                        /*
                         * Old null, new <value>
                         * Create relation with new
                         */
                        return this.updateEmployee(key, data)
                                .compose(response -> this.updateRef(current, response));
                    } else if (Ut.notNil(userId) && Ut.isNil(current)) {
                        /*
                         * Old <value>, new <null>
                         * Clear relation with old
                         */
                        return this.updateEmployee(key, data)
                                .compose(response -> this.updateRef(userId, new JsonObject())
                                        .compose(nil -> Ux.future(response))
                                );
                    } else {
                        /*
                         * Old <value>, new <value>
                         */
                        if (userId.equals(current)) {
                            /*
                             * Old = New
                             * Relation keep
                             */
                            return this.updateEmployee(key, data);
                        } else {
                            return this.updateEmployee(key, data)
                                    /*
                                     * Clear first
                                     */
                                    .compose(response -> this.updateRef(userId, new JsonObject())
                                            /*
                                             * Then update
                                             */
                                            .compose(nil -> this.updateRef(current, response)));
                        }
                    }
                }));
    }

    private Future<JsonObject> updateEmployee(final String key, final JsonObject data) {
        final JsonObject uniques = new JsonObject();
        uniques.put(KeField.KEY, key);
        final EEmployee employee = Ut.deserialize(data, EEmployee.class);
        return Ux.Jooq.on(EEmployeeDao.class)
                .upsertAsync(uniques, employee)
                .compose(Ux::fnJObject);
    }

    @Override
    public Future<Boolean> deleteAsync(final String key) {
        return this.fetchAsync(key)
                .compose(Ut.applyNil(() -> Boolean.TRUE, item -> {
                    final String userId = item.getString(USER_ID);
                    return this.updateRef(userId, new JsonObject())
                            .compose(nil -> Ux.Jooq.on(EEmployeeDao.class)
                                    .deleteByIdAsync(key));
                }));
    }

    private Future<JsonObject> updateRef(final String key, final JsonObject data) {
        return this.switchRef(data, (user, filters) -> user.updateRef(key, filters)
                .compose(Ut.applyJNil(response ->
                        Ux.future(data.put(KeField.USER_ID, response.getString(KeField.KEY))))));
    }

    private Future<JsonObject> fetchRef(final JsonObject input) {
        return this.switchRef(input, (user, filters) -> user.fetchRef(filters)
                .compose(Ut.applyJNil(response -> {
                    final String userId = response.getString(KeField.KEY);
                    if (Ut.notNil(userId)) {
                        return Ux.future(input.put(KeField.USER_ID, userId));
                    } else {
                        return Ux.future(input);
                    }
                })));
    }

    private Future<JsonObject> switchRef(final JsonObject input,
                                         final BiFunction<ExUser, JsonObject, Future<JsonObject>> executor) {
        return Ke.onTunnel(ExUser.class, JsonObject::new, user -> {
            if (Ut.isNil(input)) {
                return Ux.future(new JsonObject());
            } else {
                final JsonObject filters = new JsonObject();
                filters.put(KeField.IDENTIFIER, "employee");
                filters.put(KeField.SIGMA, input.getString(KeField.SIGMA));
                filters.put(KeField.KEY, input.getString(KeField.KEY));
                return executor.apply(user, filters);
            }
        });
    }
}
