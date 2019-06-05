package io.vertx.tp.rbac.authority;

import io.vertx.tp.rbac.cv.em.AimRole;

import java.util.Set;

/*
 * Profile information:
 * 1) User
 * 2) Group
 */
public interface ScProfile {

    Set<String> authorities(AimRole role);
}
