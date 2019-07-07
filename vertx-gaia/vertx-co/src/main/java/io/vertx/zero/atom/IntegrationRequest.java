package io.vertx.zero.atom;

import com.fasterxml.jackson.databind.JsonObjectDeserializer;
import com.fasterxml.jackson.databind.JsonObjectSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

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
}
