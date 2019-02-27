package io.zero.quiz;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;
import io.zero.epic.container.KeyPair;
import org.jooq.Condition;
import org.jooq.Operator;
import org.jooq.impl.DSL;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RunWith(VertxUnitRunner.class)
public abstract class JooqBase extends ZeroBase {

    @Rule
    public final RunTestOnContext rule = new RunTestOnContext();

    public UxJooq getDao() {
        return null;
    }

    public <T> void async(final TestContext context,
                          final Supplier<Future<T>> supplier,
                          final Consumer<T> function) {
        final UxJooq jooq = this.getDao();
        if (null != jooq) {
            final Future<T> future = supplier.get();
            this.asyncFlow(context, future, function);
        }
    }

    public <T> void asyncFlow(final TestContext context,
                              final Future<T> future,
                              final Consumer<T> function) {
        final Async async = context.async();
        future.setHandler(handler -> {
            if (handler.succeeded()) {
                function.accept(handler.result());
            } else {
                handler.cause().printStackTrace();
                context.fail(handler.cause());
            }
            async.complete();
        });
    }

    public Condition eq(final String name, final Object value) {
        return DSL.field(name).eq(value);
    }

    public Condition condAnd(final String filename) {
        final JsonObject filters = this.getJson(filename);
        return UxJooq.transform(filters, Operator.AND);
    }

    public <T> void notNull(final T entity, final TestContext context) {
        context.assertNotNull(entity);
        Annal.get(this.getClass()).debug("[ ZERO ] {0}", entity.getClass());
    }

    protected void fetchOneAsync(
            final TestContext context,
            final Class<?> clazzDao,
            final String pojo,
            final Object... args) {
        final List<KeyPair<String, Object>> keyPairs = new ArrayList<>();
        final int length = args.length / 2;
        for (int idx = 0; idx < length; idx++) {
            final int index = idx * 2;
            final String key = args[index].toString();
            final Object value = args[index + 1];
            keyPairs.add(KeyPair.create(key, value));
        }
        keyPairs.forEach(kv -> {
            UxJooq jooq = Ux.Jooq.on(clazzDao);
            if (null != pojo) {
                jooq = jooq.on(pojo);
            }
            this.asyncFlow(context,
                    jooq.fetchOneAsync(kv.getKey(), kv.getValue()),
                    context::assertNotNull);
        });
    }

    protected void fetchOneAndAsync(
            final TestContext context,
            final Class<?> clazz,
            final String pojo,
            final String... files) {
        final List<JsonObject> filters = new ArrayList<>();
        Arrays.stream(files).forEach(file -> filters.add(this.getJson(file)));
        filters.forEach(filter -> {
            UxJooq jooq = Ux.Jooq.on(clazz);
            if (null != pojo) {
                jooq = jooq.on(pojo);
            }
            this.asyncFlow(context,
                    jooq.fetchOneAndAsync(filter),
                    context::assertNotNull);
        });
    }
}
