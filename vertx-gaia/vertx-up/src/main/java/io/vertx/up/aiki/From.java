package io.vertx.up.aiki;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.StringUtil;
import io.vertx.zero.atom.Mirror;
import io.vertx.zero.atom.Mojo;

class From {

    static <T> T fromJson(final JsonObject data, final Class<T> clazz, final String pojo) {
        return Fn.get(null, () -> Fn.getSemi(StringUtil.isNil(pojo), null,
                () -> Jackson.deserialize(data, clazz),
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

