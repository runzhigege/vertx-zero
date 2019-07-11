package io.vertx.up.aiki;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Mirror;
import io.vertx.up.atom.Mojo;
import io.vertx.up.epic.Ut;
import io.vertx.up.fn.Fn;

class From {

    static <T> T fromJson(final JsonObject data, final Class<T> clazz, final String pojo) {
        return Fn.getSemi(Ut.isNil(pojo), null,
                () -> Ut.deserialize(data, clazz),
                () -> Mirror.create(From.class)
                        .mount(pojo)
                        .connect(data)
                        .type(clazz)
                        .from()
                        .get());
    }

    static JsonObject fromJson(final JsonObject criteria, final String pojo) {
        final Mojo mojo = Mirror.create(From.class).mount(pojo).mojo();
        return Query.criteria(criteria, mojo);
    }
}

