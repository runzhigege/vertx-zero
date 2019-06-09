package io.vertx.tp.rbac.authority;

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
    /*
     * Search eager of each group, each group should has only one,
     * Searched ProfileRole size = group size
     */
    static List<ProfileRole> eagerEach(final List<ProfileRole> roles) {
        /* Find groups */
        return getGroups(roles).stream().map(group -> roles.stream()
                .filter(role -> group.equals(role.getGroup().getKey()))
                /* Pickup high priority of group */
                .min(Comparator.comparing(ProfileRole::getPriority))
                .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    static List<ProfileRole> lazyEach(final List<ProfileRole> roles) {
        return getGroups(roles).stream().map(group -> roles.stream()
                .filter(role -> group.equals(role.getGroup().getKey()))
                /* Pickup low priority of group */
                .max(Comparator.comparing(ProfileRole::getPriority))
                .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    static List<ProfileRole> eager(final List<ProfileRole> roles) {
        final Integer groupPriority = findGroupPriority(roles, true);
        return roles.stream()
                .filter(role -> groupPriority.equals(role.getGroup().getPriority()))
                .collect(Collectors.toList());
    }

    static List<ProfileRole> lazy(final List<ProfileRole> roles) {
        final Integer groupPriority = findGroupPriority(roles, false);
        return roles.stream()
                .filter(role -> groupPriority.equals(role.getGroup().getPriority()))
                .collect(Collectors.toList());
    }

    /*
     * Find Group by priority
     */
    private static Integer findGroupPriority(final List<ProfileRole> roles, final boolean isHigh) {
        return isHigh ?
                roles.stream().map(ProfileRole::getGroup)
                        .min(Comparator.comparing(ProfileGroup::getPriority))
                        .map(ProfileGroup::getPriority)
                        .orElse(0) :
                roles.stream().map(ProfileRole::getGroup)
                        .max(Comparator.comparing(ProfileGroup::getPriority))
                        .map(ProfileGroup::getPriority)
                        .orElse(Integer.MAX_VALUE);
    }

    private static Set<String> getGroups(final List<ProfileRole> roles) {
        return roles.stream()
                .map(ProfileRole::getGroup)
                .map(ProfileGroup::getKey)
                .collect(Collectors.toSet());
    }
}
