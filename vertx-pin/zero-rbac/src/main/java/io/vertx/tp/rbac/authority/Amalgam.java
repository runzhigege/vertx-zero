package io.vertx.tp.rbac.authority;

import io.vertx.up.log.Annal;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Role Calculation
 * -- The calculation will based on group parameter.
 *
 */
class Amalgam {

    private static final Annal LOGGER = Annal.get(Amalgam.class);

    /*
     * Search eager of each group, each group should has only one,
     * Searched ProfileRole size = group size
     */
    static List<ProfileRole> eagerEach(final List<ProfileRole> roles) {
        /* Find groups */
        return getGroups(roles).stream().map(group -> roles.stream()
                .filter(role -> group.equals(role.getGroup().getKey()))
                /* Pickup high priority of group */
                .min(Comparator.comparing(role -> role.getGroup().getPriority()))
                .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    static List<ProfileRole> lazyEach(final List<ProfileRole> roles) {
        return getGroups(roles).stream().map(group -> roles.stream()
                .filter(role -> group.equals(role.getGroup().getKey()))
                /* Pickup low priority of group */
                .max(Comparator.comparing(role -> role.getGroup().getPriority()))
                .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    private static Set<String> getGroups(final List<ProfileRole> roles) {
        return roles.stream()
                .map(ProfileRole::getGroup)
                .map(ProfileGroup::getKey)
                .collect(Collectors.toSet());
    }
}
