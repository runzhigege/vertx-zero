package io.vertx.up.unity;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.util.Ut;

@SuppressWarnings("all")
public class UxJoin {

    private transient final JsonObject configuration = new JsonObject();
    private transient final JooqJoinder joinder = new JooqJoinder();

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
        this.joinder.add(daoCls, "key");
        return this;
    }

    public <T> UxJoin add(final Class<?> daoCls, final String field) {
        this.joinder.add(daoCls, field);
        return this;
    }

    public <T> UxJoin pojo(final Class<?> daoCls, final String pojo) {
        this.joinder.pojo(daoCls, pojo);
        return this;
    }

    public <T> UxJoin join(final Class<?> daoCls) {
        this.joinder.join(daoCls, "key");
        return this;
    }

    public <T> UxJoin join(final Class<?> daoCls, final String field) {
        this.joinder.join(daoCls, field);
        return this;
    }

    // -------------------- Search Operation -----------
    /* (Async / Sync) Sort, Projection, Criteria, Pager Search Operations */
    public Future<JsonObject> searchAsync(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return searchAsync(inquiry);
    }

    public Future<JsonObject> searchAsync(final JsonObject params) {
        final Inquiry inquiry = Query.getInquiry(params, null);
        return searchAsync(inquiry);
    }

    public Future<JsonObject> searchAsync(final Inquiry inquiry) {
        return this.joinder.searchPaginationAsync(inquiry);
    }
}
