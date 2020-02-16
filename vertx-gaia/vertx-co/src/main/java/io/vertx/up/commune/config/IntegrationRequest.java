package io.vertx.up.commune.config;

import com.fasterxml.jackson.databind.JsonObjectDeserializer;
import com.fasterxml.jackson.databind.JsonObjectSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.Objects;

/*
 * IntegrationRequest for api description
 */
public class IntegrationRequest implements Serializable {
    /*
     * Http uri definition here.
     */
    private transient String path;
    /*
     * Http method
     */
    private transient HttpMethod method;
    /*
     * Some specific situation that required headers
     */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private transient JsonObject headers = new JsonObject();

    public String getPath() {
        return this.path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(final HttpMethod method) {
        this.method = method;
    }

    public JsonObject getHeaders() {
        return this.headers;
    }

    public void setHeaders(final JsonObject headers) {
        this.headers = headers;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegrationRequest)) return false;
        final IntegrationRequest request = (IntegrationRequest) o;
        return this.path.equals(request.path) &&
                this.method == request.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.path, this.method);
    }

    @Override
    public String toString() {
        return "IntegrationRequest{" +
                "path='" + this.path + '\'' +
                ", method=" + this.method +
                ", headers=" + this.headers +
                '}';
    }
}
