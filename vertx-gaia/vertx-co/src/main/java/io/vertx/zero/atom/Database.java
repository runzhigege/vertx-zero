package io.vertx.zero.atom;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.ZJson;
import io.vertx.up.eon.em.DatabaseType;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database linker for JDBC
 */
public class Database implements Serializable, ZJson {

    private static final Annal LOGGER = Annal.get(Database.class);
    /* Database host name */
    private transient String hostname;
    /* Database instance name */
    private transient String instance;
    /* Database port number */
    private transient Integer port;
    /* Database category */
    private transient DatabaseType category;
    /* JDBC connection string */
    private transient String jdbcUrl;
    /* Database username */
    private transient String username;
    /* Database password */
    private transient String password;

    /* Database Connection Testing */
    public static boolean test(final Database database) {
        try {
            DriverManager.getConnection(database.getJdbcUrl(), database.getUsername(), database.getPassword());
            return true;
        } catch (final SQLException ex) {
            // Debug for database connection
            ex.printStackTrace();
            LOGGER.jvm(ex);
            return false;
        }
    }

    /* Database Connection Testing */
    public boolean test() {
        return test(this);
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    public String getInstance() {
        return this.instance;
    }

    public void setInstance(final String instance) {
        this.instance = instance;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }

    public DatabaseType getCategory() {
        return this.category;
    }

    public void setCategory(final DatabaseType category) {
        this.category = category;
    }

    public String getJdbcUrl() {
        return this.jdbcUrl;
    }

    public void setJdbcUrl(final String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
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

    @Override
    public JsonObject toJson() {
        return Ut.serializeJson(this);
    }

    @Override
    public void fromJson(final JsonObject data) {
        this.category = Ut.toEnum(DatabaseType.class, data.getString("category"));
        this.hostname = data.getString("hostname");
        this.port = data.getInteger("port");
        this.instance = data.getString("instance");
        this.jdbcUrl = data.getString("jdbcUrl");
        this.username = data.getString("username");
        this.password = data.getString("password");
    }
}
