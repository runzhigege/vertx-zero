package io.vertx.tp.rbac.authority;

import io.vertx.tp.rbac.cv.em.AimRole;

import java.util.Set;

public class UserProfile implements ScProfile {

    @Override
    public Set<String> authorities(final AimRole role) {
        return null;
    }
}
