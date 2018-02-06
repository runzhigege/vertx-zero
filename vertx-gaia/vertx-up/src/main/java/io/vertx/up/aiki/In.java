package io.vertx.up.aiki;

import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.Session;
import io.vertx.up.atom.Envelop;
import io.vertx.up.func.Fn;

class In {

    static <T> T request(
            final Message<Envelop> message,
            final Class<T> clazz
    ) {
        final Envelop body = message.body();
        return request(body, clazz);
    }

    static <T> T request(
            final Envelop envelop,
            final Class<T> clazz
    ) {
        return Fn.getSemi(null == envelop, null, Fn::nil,
                () -> envelop.data(clazz));
    }

    static <T> T request(
            final Message<Envelop> message,
            final Integer index,
            final Class<T> clazz) {
        final Envelop body = message.body();
        return request(body, index, clazz);
    }

    static <T> T request(
            final Envelop envelop,
            final Integer index,
            final Class<T> clazz
    ) {
        return Fn.getSemi(null == envelop, null, Fn::nil,
                () -> envelop.data(index, clazz));
    }

    static String requestUser(
            final Message<Envelop> message,
            final String field
    ) {
        return message.body().identifier(field);
    }

    static Object requestSession(
            final Message<Envelop> message,
            final String field
    ) {
        final Session session = message.body().getSession();
        return null == session ? null : session.get(field);
    }
}
