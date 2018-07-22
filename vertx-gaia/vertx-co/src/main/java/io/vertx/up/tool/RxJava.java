package io.vertx.up.tool;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashSet;

class RxJava {

    @SuppressWarnings("unchecked")
    static <T> Single<HashSet<T>> singleSet(final JsonArray data, final String field) {
        return Observable.fromIterable(data)
                .map(item -> (JsonObject) item)
                .map(item -> item.getString(field))
                .reduce(new HashSet<>(), (sets, ele) -> {
                    sets.add((T) ele);
                    return sets;
                });
    }
}
