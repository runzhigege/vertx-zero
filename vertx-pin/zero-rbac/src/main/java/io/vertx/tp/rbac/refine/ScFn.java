package io.vertx.tp.rbac.refine;

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
}
