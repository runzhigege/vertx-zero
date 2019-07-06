package io.vertx.zero.atom;

import java.io.Serializable;
import java.util.Arrays;
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
 *      "publicKey": "xxx",
 *      "privateKey": "xxx",
 *      "apis":{
 *          "get.username": {
 *              "method": "POST",
 *              "uri": "/uri/getinfo"
 *          },
 *          "post.test": {
 *              "method": "GET",
 *              "uri": "/uri/getinfo"
 *          }
 *      }
 * }
 */
public class Integration implements Serializable {

    private final transient ConcurrentMap<String, IntegrationRequest> apis
            = new ConcurrentHashMap<>();
    /*
     * Restful / Web Service information ( such as jdbcUrl )
     * The target service should be: endpoint + api ( IntegrationRequest )
     */
    private transient String endpoint;
    private transient Integer port;
    private transient String username;
    private transient String password;
    private transient String hostname;
    /*
     * SSL enabled, these two fields stored
     * publicKey / privateKey
     */
    private transient byte[] publicKey;
    private transient byte[] privateKey;

    public ConcurrentMap<String, IntegrationRequest> getApis() {
        return this.apis;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    public byte[] getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(final byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(final byte[] privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        return "Integration{" +
                "apis=" + this.apis +
                ", endpoint='" + this.endpoint + '\'' +
                ", port=" + this.port +
                ", username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", hostname='" + this.hostname + '\'' +
                ", publicKey=" + Arrays.toString(this.publicKey) +
                ", privateKey=" + Arrays.toString(this.privateKey) +
                '}';
    }
}
