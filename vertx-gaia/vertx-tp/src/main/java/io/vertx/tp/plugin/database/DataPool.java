package io.vertx.tp.plugin.database;

import io.vertx.up.commune.config.Database;
import io.vertx.up.fn.Fn;
import org.jooq.DSLContext;

import javax.sql.DataSource;

/*
 * Connection Pool of third pool, the default implementation is HikariCP, in current version the connection pool
 * is not changed and could not be configured here.
 * 1) Basic JDBC configuration
 * 2) Additional configuration
 */
public interface DataPool {
    static DataPool create() {
        return create(Database.getCurrent());
    }

    static DataPool create(final Database database) {
        return Fn.pool(Pool.POOL, database.getJdbcUrl(), () -> new HikariDataPool(database));
    }

    /*
     * Executor of Jooq ( Context )
     */
    DSLContext getExecutor();

    /*
     * Data Source here
     */
    DataSource getDataSource();
}