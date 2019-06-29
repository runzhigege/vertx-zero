package io.vertx.zero.atom;

import io.vertx.core.http.HttpMethod;

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
}
