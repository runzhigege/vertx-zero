package io.vertx.zero.atom;

import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.Json;
import io.vertx.up.eon.em.DatabaseType;
import io.vertx.up.log.Annal;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;

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
