package cn.vertxup.rbac.api;

import cn.vertxup.rbac.domain.tables.daos.OUserDao;
import cn.vertxup.rbac.domain.tables.daos.RUserRoleDao;
import cn.vertxup.rbac.domain.tables.daos.SRoleDao;
import cn.vertxup.rbac.domain.tables.daos.SUserDao;
import cn.vertxup.rbac.domain.tables.pojos.OUser;
import cn.vertxup.rbac.domain.tables.pojos.RUserRole;
import cn.vertxup.rbac.domain.tables.pojos.SRole;
import cn.vertxup.rbac.domain.tables.pojos.SUser;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.optic.Credential;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.tp.plugin.excel.atom.ExRecord;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.tp.rbac.cv.Addr;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.annotations.Queue;
import io.vertx.up.commune.Envelop;
import io.vertx.up.eon.Strings;
import io.vertx.up.eon.Values;
import io.vertx.up.fn.Fn;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Queue
public class FileActor {

    @Plugin
    private transient ExcelClient client;

    @Address(Addr.User.IMPORT)
    public Future<Envelop> importFile(final Envelop request) {
        /* Import data here for result */
        final String filename = Ux.getString(request);

        final Promise<Envelop> promise = Promise.promise();
        final File file = new File(filename);
        if (file.exists()) {
            Fn.safeJvm(() -> {
                /*
                 * Read file to inputStream
                 */
                final InputStream inputStream = new FileInputStream(file);
                /*
                 * Set<ExTable>
                 */
                final Set<ExTable> tables = this.client.ingest(inputStream, true)
                        .stream().filter(Objects::nonNull)
                        .filter(item -> Objects.nonNull(item.getName()))
                        .filter(item -> item.getName().equals("S_USER"))
                        .collect(Collectors.toSet());
                /*
                 * No directory here of importing
                 */
                final List<JsonObject> prepared = new ArrayList<>();
                tables.stream().flatMap(table -> {
                    final List<JsonObject> records = table.get().stream()
                            .filter(Objects::nonNull)
                            .map(ExRecord::toJson)
                            .collect(Collectors.toList());
                    Sc.infoWeb(this.getClass(), "Table: {0}, Records: {1}", table.getName(), String.valueOf(records.size()));
                    return records.stream();
                }).forEach(record -> {
                    /*
                     * Default value injection
                     * 1）App Env:
                     * -- "sigma": "X-Sigma"
                     * -- "appId": "X-Id"
                     * -- "appKey": "X-Key"
                     */
                    final JsonObject copy = request.headersX().copy();
                    record.mergeIn(copy, true);
                    /*
                     * Required: username, mobile, email
                     */
                    if (Ke.isIn(record, KeField.USERNAME)) {
                        prepared.add(record);
                    } else {
                        Sc.warnWeb(this.getClass(), "Ignored record: {0}", record.encode());
                    }
                });

                /*
                 * Unique filters building for each
                 */
                final List<Future<JsonObject>> futures = new ArrayList<>();
                prepared.forEach(record -> {
                    /*
                     * Unique filters
                     */
                    final JsonObject complex = new JsonObject();
                    /*
                     * ( username + sigma )
                     * or
                     * ( email + sigma )
                     * or
                     * ( mobile + sigma )
                     */
                    complex.put(KeField.USERNAME, record.getString(KeField.USERNAME))
                            .put(KeField.SIGMA, record.getString(KeField.SIGMA))
                            .put(Strings.EMPTY, Boolean.TRUE);
                    /*
                    complex.put(Strings.EMPTY, Boolean.FALSE)
                            .put("$0", new JsonObject()
                                    .put(KeField.USERNAME, record.getString(KeField.USERNAME))
                                    .put(KeField.SIGMA, record.getString(KeField.SIGMA))
                                    .put(Strings.EMPTY, Boolean.TRUE)
                            )
                            .put("$1", new JsonObject()
                                    .put(KeField.EMAIL, record.getString(KeField.EMAIL))
                                    .put(KeField.SIGMA, record.getString(KeField.SIGMA))
                                    .put(Strings.EMPTY, Boolean.TRUE)
                            )
                            .put("$2", new JsonObject()
                                    .put(KeField.MOBILE, record.getString(KeField.MOBILE))
                                    .put(KeField.SIGMA, record.getString(KeField.SIGMA))
                                    .put(Strings.EMPTY, Boolean.TRUE)
                            );
                     */
                    Sc.infoWeb(this.getClass(), "Unique filters: {0}", complex.encode());
                    futures.add(this.saveAsync(complex, record, request));
                });
                final Future<JsonArray> result = Ux.thenCombine(futures);
                result.setHandler(imported -> {
                    Sc.infoWeb(this.getClass(), "File imported: {0}", filename);
                    promise.complete(Envelop.success(Boolean.TRUE));
                });

            });
        } else {
            promise.complete(Envelop.success(Boolean.FALSE));
        }
        return promise.future();
    }

    private Future<SUser> createToken(final SUser user) {
        return Ke.channelAsync(Credential.class,
                () -> Ux.future(user),
                stub -> stub.fetchAsync(user.getSigma())
                        .compose(credential -> Ux.future(new OUser()
                                .setActive(Boolean.TRUE)
                                .setKey(UUID.randomUUID().toString())
                                .setClientId(user.getKey())
                                .setClientSecret(Ut.randomString(64))
                                .setScope(credential.getString(KeField.REALM))
                                .setLanguage(user.getLanguage())
                                .setGrantType(credential.getString(KeField.GRANT_TYPE))))
                        .compose(ouser -> Ux.Jooq.on(OUserDao.class).insertAsync(ouser))
                        .compose(tokend -> Ux.future(user))
        );
    }

    private Future<JsonObject> saveRel(final SUser user, final JsonObject record, final List<SRole> roles) {
        final String roleNames = record.getString("roles");
        if (Objects.isNull(user) || Ut.isNil(roleNames)) {
            /*
             * Not needed
             */
            Sc.infoWeb(this.getClass(), "Skip relation building: {0}, {1}", roleNames, user);
            return Ux.future(user).compose(Ux::fnJObject);
        } else {
            /*
             * Remove old relation ship between ( role - user )
             */
            return Ux.Jooq.on(RUserRoleDao.class).deleteAsync(new JsonObject().put(KeField.USER_ID, user.getKey())).compose(deleted -> {
                /*
                 * Grouped list by `key`
                 */
                final ConcurrentMap<String, String> grouped = new ConcurrentHashMap<>();
                roles.stream().filter(Objects::nonNull).forEach(role -> grouped.put(role.getName(), role.getKey()));
                /*
                 * Calculated for name = A,B,C
                 */
                final List<String> validRoles = Arrays.stream(roleNames.split(","))
                        .map(String::trim)
                        .filter(grouped::containsKey)
                        .collect(Collectors.toList());
                /*
                 * Building relation ship
                 */
                Sc.infoWeb(this.getClass(), "Will build size = {0}", String.valueOf(validRoles.size()));
                final List<RUserRole> relationList = new ArrayList<>();
                Ut.itList(validRoles, (name, index) -> relationList.add(
                        new RUserRole().setUserId(user.getKey()).setRoleId(grouped.get(name)).setPriority(index)
                ));
                return Ux.Jooq.on(RUserRoleDao.class).insertAsync(relationList)
                        .compose(inserted -> Ux.future(user).compose(Ux::fnJObject));
            });
        }
    }

    private Future<JsonObject> saveAsync(final JsonObject filters, final JsonObject record, final Envelop envelop) {
        return Ux.Jooq.on(SUserDao.class).findAsync(filters)
                .compose(Ux::fnJArray)
                .compose(result -> {
                    if (1 < result.size()) {
                        Sc.infoWeb(this.getClass(), "Conflicts because of multi results found: {0}", result.encode());
                        return Ke.Result.boolAsync(false);
                    } else {
                        return Ux.Jooq.on(SRoleDao.class).<SRole>findAllAsync()
                                .compose(roles -> {
                                    final String userId = Ke.keyUser(envelop);
                                    if (0 == result.size()) {
                                        /*
                                         * Insert User
                                         */
                                        final JsonObject inserted = record.copy();
                                        inserted.put(KeField.KEY, UUID.randomUUID().toString());
                                        inserted.put(KeField.CREATED_BY, userId);
                                        inserted.put(KeField.CREATED_AT, Instant.now());
                                        inserted.put(KeField.UPDATED_BY, userId);
                                        inserted.put(KeField.UPDATED_AT, Instant.now());
                                        final SUser user = Ux.fromJson(inserted, SUser.class);
                                        user.setActive(Boolean.TRUE);
                                        /*
                                         * Default password hard coding: 12345678
                                         */
                                        user.setPassword("25D55AD283AA400AF464C76D713C07AD");
                                        return Ux.Jooq.on(SUserDao.class).insertAsync(user)
                                                /*
                                                 * Only new user need to create oauth token
                                                 */
                                                .compose(this::createToken)
                                                .compose(insertUser -> this.saveRel(insertUser, record, roles));
                                    } else {
                                        /*
                                         * Update User
                                         */
                                        JsonObject updated = result.getJsonObject(Values.IDX);
                                        if (Ut.notNil(updated)) {
                                            updated = updated.copy();
                                            updated.mergeIn(record);
                                            updated.put(KeField.UPDATED_BY, userId);
                                            updated.put(KeField.UPDATED_AT, Instant.now());
                                        }
                                        final SUser user = Ux.fromJson(updated, SUser.class);
                                        user.setActive(Boolean.TRUE);
                                        return Ux.Jooq.on(SUserDao.class).updateAsync(user)
                                                .compose(insertUser -> this.saveRel(insertUser, record, roles));
                                    }
                                });

                    }
                });
    }
}
