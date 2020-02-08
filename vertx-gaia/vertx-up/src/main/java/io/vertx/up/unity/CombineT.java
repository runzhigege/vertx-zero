package io.vertx.up.unity;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.up.util.Ut;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("all")
class CombineT {

    static <T> Future<List<T>> thenCombine(final List<Future<T>> futures) {
        final List<Future> futureList = new ArrayList<>(futures);
        return CompositeFuture.join(futureList).compose(finished -> {
            final List<T> result = new ArrayList<>();
            if (null != finished) {
                Ut.itList(finished.list(), (item, index) -> {
                    if (Objects.nonNull(item)) {
                        result.add((T) item);
                    }
                });
            }
            return Future.succeededFuture(result);
        });
    }

    static <T> Future<List<T>> thenCombineArray(final List<Future<List<T>>> futures) {
        final List<Future> futureList = new ArrayList<>(futures);
        return CompositeFuture.join(futureList).compose(finished -> {
            final List<T> result = new ArrayList<>();
            if (null != finished) {
                Ut.itList(finished.list(), (item, index) -> {
                    if (item instanceof List) {
                        final List<T> grouped = (List<T>) item;
                        if (!grouped.isEmpty()) {
                            result.addAll(grouped);
                        }
                    }
                });
            }
            return Future.succeededFuture(result);
        });
    }
}
