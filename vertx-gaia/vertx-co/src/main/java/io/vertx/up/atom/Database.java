package io.vertx.up.atom;

import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.Json;
import io.vertx.up.eon.em.DatabaseType;
import io.vertx.up.log.Annal;
import io.vertx.up.epic.Ut;
import io.vertx.up.uca.marshal.node.Node;
import io.vertx.up.uca.marshal.node.ZeroUniform;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Database linker for JDBC
 */
public class Database implements Serializable, Json {

    private static final Annal LOGGER = Annal.get(Database.class);
    private static final Node<JsonObject> VISITOR = Ut.singleton(ZeroUniform.class);
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

    /*
     * Get current jooq configuration for Application / Source
     */
    public static Database getCurrent() {
        final JsonObject raw = VISITOR.read();
        final JsonObject jooq = Ut.visitJObject(raw, "jooq", "provider");
        final Database database = new Database();
        /*
         * type of database
         */
        final String type = jooq.getString("type");
        if (Ut.isNil(type)) {
            database.setCategory(DatabaseType.MYSQL5);
        } else {
            final DatabaseType databaseType = Ut.toEnum(DatabaseType.class, type);
            database.setCategory(null == databaseType ? DatabaseType.MYSQL5 : databaseType);
        }
        database.setInstance(jooq.getString("catalog"));
        database.setJdbcUrl(jooq.getString("jdbcUrl"));
        database.setUsername(jooq.getString("username"));
        database.setPassword(jooq.getString("password"));
        return database;
    }

    /* Database Connection Testing */
    public boolean test() {
        return test(this);
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(final String instance) {
        this.instance = instance;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }

    public DatabaseType getCategory() {
        return category;
    }

    public void setCategory(final DatabaseType category) {
        this.category = category;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(final String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
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

    @Override
    public JsonObject toJson() {
        return Ut.serializeJson(this);
    }

    @Override
    public void fromJson(final JsonObject data) {
        category = Ut.toEnum(DatabaseType.class, data.getString("category"));
        hostname = data.getString("hostname");
        port = data.getInteger("port");
        instance = data.getString("instance");
        jdbcUrl = data.getString("jdbcUrl");
        username = data.getString("username");
        password = data.getString("password");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Database)) {
            return false;
        }
        final Database database = (Database) o;
        return jdbcUrl.equals(database.jdbcUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jdbcUrl);
    }
}
