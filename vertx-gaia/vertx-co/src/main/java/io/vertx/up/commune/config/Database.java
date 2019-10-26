package io.vertx.up.commune.config;

import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.Json;
import io.vertx.up.eon.em.DatabaseType;
import io.vertx.up.log.Annal;
import io.vertx.up.uca.yaml.Node;
import io.vertx.up.uca.yaml.ZeroUniform;
import io.vertx.up.util.Ut;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/*
 * Database linker for JDBC
 * {
 *      "hostname": "localhost",
 *      "instance": "DB_ORIGIN_X",
 *      "username": "lang",
 *      "password": "xxxx",
 *      "port": 3306,
 *      "category": "MYSQL5",
 *      "driverClassName": "Fix driver issue here",
 *      "jdbcUrl": "jdbc:mysql://ox.engine.cn:3306/DB_ORIGIN_X?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&useSSL=false",
 * }
 * I_SERVICE -> configDatabase
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
    /* Database driver class */
    private transient String driverClassName;

    /* Database Connection Testing */
    public static boolean test(final Database database) {
        try {
            DriverManager.getConnection(database.getJdbcUrl(), database.getUsername(), database.getPassword());
            return true;
        } catch (final SQLException ex) {
            // Debug for database connection
            ex.printStackTrace();
            Database.LOGGER.jvm(ex);
            return false;
        }
    }

    /*
     * Get current jooq configuration for Application / Source
     */
    public static Database getCurrent() {
        final JsonObject raw = Database.VISITOR.read();
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
        database.setDriverClassName(jooq.getString("driverClassName"));
        return database;
    }

    /* Database Connection Testing */
    public boolean test() {
        return Database.test(this);
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

    public String getDriverClassName() {
        return this.driverClassName;
    }

    public void setDriverClassName(final String driverClassName) {
        this.driverClassName = driverClassName;
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
        this.driverClassName = data.getString("driverClassName");
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
        return this.jdbcUrl.equals(database.jdbcUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.jdbcUrl);
    }
}
