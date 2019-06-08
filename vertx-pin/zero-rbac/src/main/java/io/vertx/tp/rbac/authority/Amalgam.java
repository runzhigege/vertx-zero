package io.vertx.tp.rbac.authority;

import io.vertx.up.log.Annal;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Group Calculation
 */
class Amalgam {

    private static final Annal LOGGER = Annal.get(Amalgam.class);

    static List<ProfileRole> eager(final List<ProfileRole> roles) {
        return findRoles(roles, true);
    }

    static List<ProfileRole> lazy(final List<ProfileRole> roles) {
        return findRoles(roles, false);
    }

    private static List<ProfileRole> findRoles(final List<ProfileRole> roles,
                                               final boolean priority) {

        final Integer foundPriority;
        if (priority) {
            foundPriority = roles.stream()
                    .min(Comparator.comparing(role -> role.getGroup().getPriority()))
                    .map(ProfileRole::getGroup)
                    .map(ProfileGroup::getPriority)
                    .orElse(0);
        } else {
            foundPriority = roles.stream()
                    .max(Comparator.comparing(role -> role.getGroup().getPriority()))
                    .map(ProfileRole::getGroup)
                    .map(ProfileGroup::getPriority)
                    .orElse(Integer.MAX_VALUE);
        }
        return roles.stream()
                .filter(item -> foundPriority.equals(item.getPriority()))
                .collect(Collectors.toList());
    }
}
