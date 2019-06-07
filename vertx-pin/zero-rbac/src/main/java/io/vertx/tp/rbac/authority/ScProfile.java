package io.vertx.tp.rbac.authority;

import cn.vertxup.domain.tables.daos.RRolePermDao;
import cn.vertxup.domain.tables.pojos.SPermission;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Ux;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Single authority for role -> permissions
 * 1) priority
 * 2) permissions
 */
public class ScProfile implements Serializable {

    private static final ScConfig CONFIG = ScPin.getConfig();
    private transient final String roleId;
    private transient final Integer priority;
    private transient final Set<String> authorities = new HashSet<>();

    public ScProfile(final JsonObject data) {
        /* Role Id */
        this.roleId = data.getString(AuthKey.F_ROLE_ID);
        /* Priority */
        this.priority = data.getInteger(AuthKey.PRIORITY);
    }

    public Future<ScProfile> init() {
        /* Fetch permission */
        final boolean isSecondary = CONFIG.getSupportSecondary();
        if (isSecondary) {
            /* Enabled secondary permission */
            return this.fetchFull();
        } else {
            /* No secondary */
            return this.fetchData();
        }
    }

    public Integer getPriority() {
        return this.priority;
    }

    public Set<String> getAuthorities() {
        return this.authorities;
    }

    private Future<ScProfile> fetchFull() {
        return Sc.<JsonArray>cachePermission(this.roleId).compose(array -> {
            if (Objects.nonNull(array)) {
                /* Identify the data from Pool */
                return this.process(array.getList());
            } else {
                /* Not found in pool */
                return this.fetchData()
                        /* Fetch result */
                        .compose(item -> this.process(item.authorities))
                        /* Stored data into pool  */
                        .compose(authorities -> Sc.cachePermission(this.roleId, authorities))
                        /* Fill current authorities */
                        .compose(authorities -> this.process(authorities.getList()));
            }
        });
    }

    private Future<ScProfile> fetchData() {
        return Ux.Jooq.on(RRolePermDao.class)
                .<SPermission>fetchAsync(AuthKey.F_ROLE_ID, this.roleId)
                .compose(permission -> Ux.toFuture(permission.stream()
                        .filter(Objects::nonNull)
                        .map(SPermission::getKey)
                        .collect(Collectors.toList())
                ))
                .compose(Ux::fnJArray)
                .compose(authorities -> Sc.cachePermission(this.roleId, authorities))
                /* Fill current authorities */
                .compose(authorities -> this.process(authorities.getList()));
    }

    private Future<JsonArray> process(final Set<String> authoritySet) {
        final JsonArray authorities = new JsonArray();
        authoritySet.forEach(authorities::add);
        return Future.succeededFuture(authorities);
    }

    private Future<ScProfile> process(final List list) {
        for (final Object key : list) {
            if (Objects.nonNull(key)) {
                final String permissionKey = (String) key;
                this.authorities.add(permissionKey);
            }
        }
        return Future.succeededFuture(this);
    }
}
