package io.vertx.tp.database;

import io.vertx.zero.atom.Database;
import io.zero.epic.fn.Fn;
import org.jooq.DSLContext;

import javax.sql.DataSource;

/*
 * Connection Pool of third pool, the default implementation is HikariCP, in current version the connection pool
 * is not changed and could not be configured here.
 * 1) Basic JDBC configuration
 * 2) Additional configuration
 */
public interface ZPool {
    static ZPool create() {
        return create(Database.getCurrent());
    }

    static ZPool create(final Database database) {
        return Fn.pool(Pool.POOL, database.getJdbcUrl(), () -> new HikariZPool(database));
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
