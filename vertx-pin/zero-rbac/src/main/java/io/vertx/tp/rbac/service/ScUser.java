package io.vertx.tp.rbac.service;

import cn.vertxup.domain.tables.daos.OUserDao;
import cn.vertxup.domain.tables.daos.SUserDao;
import cn.vertxup.domain.tables.pojos.SUser;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._401PasswordWrongException;
import io.vertx.tp.error._449UserNotFoundException;
import io.vertx.up.aiki.Uson;
import io.vertx.up.aiki.Ux;
import io.zero.epic.fn.Fn;

import java.util.Arrays;

class ScUser {
    private static final String AUTH_SUCCESS =
            "[ Ακριβώς ] User ( username = {0} ) login successfully.";
    private static final String AUTH_USER =
            "[ Ακριβώς ] username = {0} does not exist.";
    private static final String AUTH_PWD =
            "[ Ακριβώς ] username = {0}, the password ( {1} ) you provided is wrong.";

    private static ScUser INSTANCE;

    private ScUser() {
    }

    static ScUser create() {
        if (null == INSTANCE) {
            INSTANCE = new ScUser();
        }
        return INSTANCE;
    }

    @SuppressWarnings("all")
    Future<JsonObject> login(final String username, final String password) {
        return Ux.Jooq.on(SUserDao.class)
                /* Find the user record with username */
                .<SUser>fetchOneAsync("username", username)
                .compose(pojo -> Fn.match(() -> Fn.fork(
                        // Login successfully
                        () -> Ux.log(this.getClass()).on(AUTH_SUCCESS).info(username),
                        () -> Ux.toFuture(pojo)),
                        // User Not Found
                        Fn.branch(null == pojo,
                                () -> Ux.log(this.getClass()).on(AUTH_USER).info(username),
                                () -> Ux.thenError(_449UserNotFoundException.class, this.getClass(), username)
                        ),
                        // Password Wrong
                        Fn.branch(null != pojo && !password.equals(pojo.getPassword()),
                                () -> Ux.log(this.getClass()).on(AUTH_PWD).info(username, password),
                                () -> Ux.thenError(_401PasswordWrongException.class, this.getClass(), username))
                ))
                .compose(Ux::fnJObject)
                .compose(item -> Ux.thenParallelJson(item,
                        // Secondar query
                        (user) -> Arrays.asList(
                                // Fetch Oauth user
                                this.fetchOUser(user)
                        ),
                        Ux::appendJson, // SUser / OUser - Avoid duplicated merging
                        Ux::appendJson  // SUser / Related
                ))
                .compose(item -> Uson.create(item).pickup(
                        "key",          /* client_id parameter */
                        "scope",        /* scope parameter */
                        "state",        /* state parameter */
                        "clientSecret", /* client_secret parameter */
                        "grantType"     /* grant_type parameter */
                ).denull().toFuture());
    }

    private Future<JsonObject> fetchOUser(final JsonObject user) {
        return Ux.Jooq.on(OUserDao.class)
                .fetchOneAsync("clientId", user.getString("key"))
                .compose(Ux::fnJObject);
    }
}
