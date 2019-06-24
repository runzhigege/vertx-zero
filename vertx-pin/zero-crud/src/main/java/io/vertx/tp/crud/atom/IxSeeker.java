package io.vertx.tp.crud.atom;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Resource Seeker component
 */
public class IxSeeker implements Serializable {
    private static final ConcurrentMap<String, IxSeeker> SEEKER_MAP = new ConcurrentHashMap<>();
    private transient HttpMethod fromMethod;
    private transient HttpMethod toMethod;
    private transient String fromUri;
    private transient String toUri;

    private IxSeeker(final String key, final String value) {
        final JsonObject from = Ut.netStatus(key);
        final JsonObject to = Ut.netStatus(value);
        /* Source Uri */
        if (!Ut.isNil(from)) {
            this.fromMethod = Ut.toEnum(HttpMethod.class, from.getString("method"));
            this.fromUri = from.getString("uri");
        }
        /* Target Uri */
        if (!Ut.isNil(to)) {
            this.toMethod = Ut.toEnum(HttpMethod.class, to.getString("method"));
            this.toUri = to.getString("uri");
        }
    }

    public static IxSeeker create(final String key, final String value) {
        return Fn.pool(SEEKER_MAP, key, () -> new IxSeeker(key, value));
    }

    public static IxSeeker get(final String key) {
        return SEEKER_MAP.getOrDefault(key, null);
    }

    public static Integer size() {
        return SEEKER_MAP.size();
    }

    public HttpMethod getFromMethod() {
        return this.fromMethod;
    }

    public HttpMethod getToMethod() {
        return this.toMethod;
    }

    public String getFromUri() {
        return this.fromUri;
    }

    public String getToUri() {
        return this.toUri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IxSeeker)) {
            return false;
        }
        final IxSeeker ixSeeker = (IxSeeker) o;
        return this.fromMethod == ixSeeker.fromMethod &&
                this.toMethod == ixSeeker.toMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.fromMethod);
    }
}
