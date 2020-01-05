package io.vertx.up.unity;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.pojo.Mirror;
import io.vertx.up.atom.pojo.Mojo;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@SuppressWarnings("all")
public class UxJoin {

    private transient final JsonObject configuration = new JsonObject();
    private transient final JooqJoinder joinder = new JooqJoinder();

    private transient final ConcurrentMap<Class<?>, String> POJO_MAP
            = new ConcurrentHashMap<>();
    private transient Mojo merged = null;

    UxJoin(final String file) {
        if (Ut.notNil(file)) {
            final JsonObject config = Ut.ioJObject(file);
            if (Ut.notNil(config)) {
                /*
                 * Only one level for mapping configuration
                 * - field -> sourceTable
                 */
                configuration.mergeIn(config);
            }
        }
    }

    public <T> UxJoin add(final Class<?> daoCls) {
        this.joinder.add(daoCls, this.translate(daoCls, "key"));
        return this;
    }

    public <T> UxJoin add(final Class<?> daoCls, final String field) {
        this.joinder.add(daoCls, this.translate(daoCls, field));
        return this;
    }

    public <T> UxJoin pojo(final Class<?> daoCls, final String pojo) {
        final Mojo created = Mirror.create(UxJoin.class).mount(pojo).mojo();
        this.POJO_MAP.put(daoCls, pojo);
        if (Objects.isNull(this.merged)) {
            this.merged = new Mojo();
        }
        this.merged.put(created.getInAll());
        return this;
    }

    public <T> UxJoin join(final Class<?> daoCls) {
        this.joinder.join(daoCls, this.translate(daoCls, "key"));
        return this;
    }

    public <T> UxJoin join(final Class<?> daoCls, final String field) {
        this.joinder.join(daoCls, this.translate(daoCls, field));
        return this;
    }

    private String translate(final Class<?> daoCls, final String field) {
        final String pojoFile = this.POJO_MAP.get(daoCls);
        final Mojo mojo = Mirror.create(UxJoin.class).mount(pojoFile).mojo();
        if (Objects.isNull(mojo)) {
            return field;
        } else {
            final String translated = mojo.getIn().get(field);
            if (Ut.isNil(translated)) {
                return field;
            } else {
                return translated;
            }
        }
    }

    // -------------------- Search Operation -----------
    /* (Async / Sync) Sort, Projection, Criteria, Pager Search Operations */

    public Future<JsonObject> searchAsync(final JsonObject params) {
        final Inquiry inquiry = Query.getInquiry(params, this.merged);
        return searchAsync(inquiry);
    }

    public Future<JsonObject> searchAsync(final Inquiry inquiry) {
        this.POJO_MAP.forEach(this.joinder::pojo);
        return this.joinder.searchPaginationAsync(inquiry, this.merged);
    }
}
