package io.vertx.tp.rbac.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.up.aiki.Uarr;
import io.vertx.up.aiki.Ux;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

class ScFn {

    static <T, R> List<R> reduce(final List<T> list, final Function<T, R> function) {
        return list.stream().filter(Objects::nonNull)
                .map(function).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    static <T> Future<JsonArray> relation(final String field, final String userKey, final Class<?> daoCls) {
        return Ux.Jooq.on(daoCls).<T>fetchAsync(field, userKey)
                .compose(Ux::fnJArray)
                .compose(relation -> Uarr.create(relation)
                        .remove(field).toFuture());
    }
}
