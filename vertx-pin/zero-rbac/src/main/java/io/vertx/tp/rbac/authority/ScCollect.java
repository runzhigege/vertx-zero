package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.zero.epic.Ut;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

class ScCollect {

    static Consumer<JsonObject> union(final ProfileType type, final List<ScProfile> profiles) {
        return bind(type, profiles, Ut::union);
    }

    static Consumer<JsonObject> intersect(final ProfileType type, final List<ScProfile> profiles) {
        return bind(type, profiles, Ut::intersect);
    }

    static Consumer<JsonObject> eager(final ProfileType type, final List<ScProfile> profiles) {
        return bind(type, profiles, true);
    }

    static Consumer<JsonObject> lazy(final ProfileType type, final List<ScProfile> profiles) {
        return bind(type, profiles, false);
    }

    private static Consumer<JsonObject> bind(final ProfileType type,
                                             final List<ScProfile> profiles,
                                             final BinaryOperator<Set<String>> fnReduce) {
        return input -> {
            if (Objects.nonNull(input) && !profiles.isEmpty()) {
                final ScProfile first = profiles.iterator().next();
                /* Input process */
                final Set<String> permissionIds = profiles.stream()
                        .filter(Objects::nonNull)
                        .map(ScProfile::getAuthorities)
                        .reduce(first.getAuthorities(), fnReduce);
                /* JsonArray */
                final JsonArray data = Ut.toJArray(permissionIds);
                input.put(type.getKey(), data);
            }
        };
    }

    private static Consumer<JsonObject> bind(final ProfileType type,
                                             final List<ScProfile> profiles,
                                             final boolean highPriority) {
        return input -> {
            if (Objects.nonNull(input) && !profiles.isEmpty()) {
                /* Find */
                final Set<String> found;
                if (highPriority) {
                    found = profiles.stream()
                            .min(Comparator.comparing(ScProfile::getPriority))
                            .map(ScProfile::getAuthorities)
                            .orElse(new HashSet<>());
                } else {
                    found = profiles.stream()
                            .max(Comparator.comparing(ScProfile::getPriority))
                            .map(ScProfile::getAuthorities)
                            .orElse(new HashSet<>());
                }
                final JsonArray data = Ut.toJArray(found);
                input.put(type.getKey(), data);
            }
        };
    }
}
