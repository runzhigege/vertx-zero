package io.vertx.up.aiki;

import io.vertx.core.json.JsonObject;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.zero.atom.Mirror;
import io.vertx.zero.atom.Mojo;

class From {

    static <T> T fromJson(final JsonObject data, final Class<T> clazz, final String pojo) {
        return Fn.getNull(Instance.instance(clazz), () -> Fn.getSemi(Ut.isNil(pojo), null,
                () -> Ut.deserialize(data, clazz),
                () -> Mirror.create(From.class)
                        .mount(pojo)
                        .connect(data)
                        .from()
                        .get()), pojo);
    }

    static JsonObject fromJson(final JsonObject criteria, final String pojo) {
        final Mojo mojo = Mirror.create(From.class).mount(pojo).mojo();
        return Query.criteria(criteria, mojo);
    }
}

