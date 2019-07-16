package io.vertx.up.plugin.redis;

import java.io.Serializable;

/*
 * Redis extra configuration
 */
public class RedisExtra implements Serializable {

    static String DEFAULT_SESSION_MAP_NAME = "vertx-web.sessions";

    private int port = 6379;
    private String host = "localhost";
    private long retryTimeout = 2 * 1000;
    private String auth;

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public long getRetryTimeout() {
        return retryTimeout;
    }

    public void setRetryTimeout(final long retryTimeout) {
        this.retryTimeout = retryTimeout;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(final String auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "RedisExtra{" +
                "port=" + port +
                ", host='" + host + '\'' +
                ", retryTimeout=" + retryTimeout +
                ", auth='" + auth + '\'' +
                '}';
    }
}
