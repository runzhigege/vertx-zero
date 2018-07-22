package io.vertx.up.tool;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.rx.ReduceVerticalException;

import java.util.HashSet;
import java.util.Set;

class RxJava {

    private static final Annal LOGGER = Annal.get(RxJava.class);

    @SuppressWarnings("unchecked")
    static <T> T rxOneElement(final JsonArray data, final String field) {
        final Single<Set<T>> source = rxSet(data, field);
        final Set<T> result = source.blockingGet();
        // Only one unique element allowed
        Fn.flingUp(Values.ONE != result.size(), LOGGER,
                ReduceVerticalException.class, RxJava.class, data, field, result);
        return result.iterator().next();
    }

    @SuppressWarnings("unchecked")
    static <T> Single<Set<T>> rxSet(final JsonArray data, final String field) {
        return Observable.fromIterable(data)
                .map(item -> (JsonObject) item)
                .map(item -> item.getValue(field))
                .reduce(new HashSet<>(), (sets, ele) -> {
                    sets.add((T) ele);
                    return sets;
                });
    }
}
