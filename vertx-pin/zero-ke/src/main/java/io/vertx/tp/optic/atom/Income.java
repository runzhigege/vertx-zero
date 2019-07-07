package io.vertx.tp.optic.atom;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.init.KePin;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.log.Annal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Stack of fluent api here to store parameters
 */
@SuppressWarnings("all")
public class Income implements Serializable {
    private static final Annal LOGGER = Annal.get(Income.class);
    private final transient Queue<Object> queue = new PriorityQueue();
    private final transient Class<?> key;
    private final transient List<String> names = new ArrayList<>();

    private Income(final Class<?> key) {
        this.key = key;
        final Lexeme lexeme = KePin.get(key);
        this.names.addAll(lexeme.params());
    }

    public static Income in(final Class<?> key) {
        return new Income(key);
    }

    public <T> Income in(final T value) {
        queue.offer(value);
        return this;
    }

    public JsonObject arguments() {
        final JsonObject arguments = new JsonObject();
        this.names.forEach(item -> {
            final String field = item;
            Object value = null;
            if (!this.queue.isEmpty()) {
                value = this.queue.poll();
            }
            Ke.infoKe(LOGGER, "[ Income ] field = {0}, value = {1}", field, value);
            arguments.put(field, value);
        });
        return arguments;
    }
}
