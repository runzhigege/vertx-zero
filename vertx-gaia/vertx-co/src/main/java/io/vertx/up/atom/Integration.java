package io.vertx.up.atom;

import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.Json;
import io.vertx.up.epic.Ut;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * DTO for third part integration basic configuration instead of other
 * {
 *      "endpoint": "http://www.demo.cn/ws/api/",
 *      "port": 1234,
 *      "username": "lang",
 *      "password": "xxxx",
 *      "hostname": "www.demo.cn or 192.168.0.12",
 *      "publicKeyFile": "public key path",
 *      "apis":{
 *          "get.username": {
 *              "method": "POST",
 *              "uri": "/uri/getinfo",
 *              "headers": {}
 *          },
 *          "post.test": {
 *              "method": "GET",
 *              "uri": "/uri/getinfo",
 *              "headers": {}
 *          }
 *      }
 * }
 */
public class Integration implements Json, Serializable {

    private final transient ConcurrentMap<String, IntegrationRequest> apis
            = new ConcurrentHashMap<>();
    /*
     * Restful / Web Service information ( such as jdbcUrl )
     * The target service should be: endpoint + api ( IntegrationRequest )
     */
    private transient String endpoint;
    private transient Integer port;
    private transient String username;
    /*
     * SSL enabled, these two fields stored
     * 1) publicKeyFile
     * 2) Authentication
     */
    private transient String password;
    private transient String hostname;
    private transient String publicKeyFile;

    public ConcurrentMap<String, IntegrationRequest> getApis() {
        return apis;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    public String getPublicKeyFile() {
        return publicKeyFile;
    }

    public void setPublicKeyFile(final String publicKeyFile) {
        this.publicKeyFile = publicKeyFile;
    }

    @Override
    public JsonObject toJson() {
        return Ut.serializeJson(this);
    }

    @Override
    public void fromJson(final JsonObject data) {
        endpoint = data.getString("endpoint");
        hostname = data.getString("hostname");
        username = data.getString("username");
        password = data.getString("password");
        port = data.getInteger("port");
        publicKeyFile = data.getString("publicKeyFile");
        /*
         * Integration Request
         */
        final JsonObject apis = data.getJsonObject("apis");
        if (Ut.notNil(apis)) {
            Ut.<JsonObject>itJObject(apis, (json, field) -> {
                final IntegrationRequest request = Ut.deserialize(json, IntegrationRequest.class);
                this.apis.put(field, request);
            });
        }
    }

    public IntegrationRequest createRequest(final String key) {
        final IntegrationRequest request = new IntegrationRequest();
        final IntegrationRequest original = apis.get(key);
        request.setHeaders(original.getHeaders().copy());
        request.setMethod(original.getMethod());
        request.setPath(endpoint + original.getPath());
        return request;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Integration)) {
            return false;
        }
        final Integration that = (Integration) o;
        return endpoint.equals(that.endpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpoint);
    }

    @Override
    public String toString() {
        return "Integration{" +
                "apis=" + apis +
                ", endpoint='" + endpoint + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hostname='" + hostname + '\'' +
                ", publicKeyFile='" + publicKeyFile + '\'' +
                '}';
    }
}
