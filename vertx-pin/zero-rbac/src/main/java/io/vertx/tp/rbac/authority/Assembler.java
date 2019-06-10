package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.zero.epic.Ut;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/*
 * Role Calculation
 * -- No filter for ProfileRole calculation, all the calculation will apply to
 * all input profiles.
 */
public class Assembler {
    public static Consumer<JsonObject> union(final ProfileType type, final List<ProfileRole> profiles) {
        return bind(type, profiles, Ut::union);
    }

    public static Consumer<JsonObject> intersect(final ProfileType type, final List<ProfileRole> profiles) {
        return bind(type, profiles, Ut::intersect);
    }

    public static Consumer<JsonObject> eager(final ProfileType type, final List<ProfileRole> profiles) {
        return bind(type, profiles, true);
    }

    public static Consumer<JsonObject> lazy(final ProfileType type, final List<ProfileRole> profiles) {
        return bind(type, profiles, false);
    }

    private static Consumer<JsonObject> bind(final ProfileType type,
                                             final List<ProfileRole> profiles,
                                             final BinaryOperator<Set<String>> fnReduce) {
        return input -> {
            if (Objects.nonNull(input) && !profiles.isEmpty()) {
                final ProfileRole first = profiles.iterator().next();
                /* Input process */
                final Set<String> permissionIds = profiles.stream()
                        .filter(Objects::nonNull)
                        .map(ProfileRole::getAuthorities)
                        .reduce(first.getAuthorities(), fnReduce);
                /* JsonArray */
                final JsonArray data = Ut.toJArray(permissionIds);
                input.put(type.getKey(), data);
            }
        };
    }

    private static Consumer<JsonObject> bind(final ProfileType type,
                                             final List<ProfileRole> profiles,
                                             final boolean highPriority) {
        return input -> {
            if (Objects.nonNull(input) && !profiles.isEmpty()) {
                /* Find */
                final Set<String> found;
                if (highPriority) {
                    found = profiles.stream()
                            .min(Comparator.comparing(ProfileRole::getPriority))
                            .map(ProfileRole::getAuthorities)
                            .orElse(new HashSet<>());
                } else {
                    found = profiles.stream()
                            .max(Comparator.comparing(ProfileRole::getPriority))
                            .map(ProfileRole::getAuthorities)
                            .orElse(new HashSet<>());
                }
                final JsonArray data = Ut.toJArray(found);
                input.put(type.getKey(), data);
            }
        };
    }
}
