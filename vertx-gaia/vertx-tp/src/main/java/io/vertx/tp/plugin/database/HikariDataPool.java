package io.vertx.tp.database;

import com.zaxxer.hikari.HikariDataSource;
import io.vertx.up.commune.config.Database;
import io.vertx.up.log.Annal;
import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultConnectionProvider;

import java.sql.SQLException;
import java.util.Objects;

public class HikariDataPool implements DataPool {
    private static final Annal LOGGER = Annal.get(HikariDataPool.class);
    private final transient Database database;
    /* Each jdbc url has one Pool here **/
    private transient DSLContext context;
    private transient HikariDataSource dataSource;

    HikariDataPool(final Database database) {
        this.database = database;
    }

    private void initDelay() {
        /*
         * Initializing data source
         */
        initJdbc();
        /*
         * Initializing data source pool
         */
        initPool();
        /*
         * Initializing data source of jooq
         */
        initJooq();
    }

    @Override
    public DSLContext getExecutor() {
        if (Objects.isNull(context)) {
            initDelay();
        }
        return context;
    }

    @Override
    public HikariDataSource getDataSource() {
        if (Objects.isNull(dataSource)) {
            initDelay();
        }
        return dataSource;
    }

    private void initJooq() {
        if (null == context) {
            try {
                /* Init Jooq configuration */
                final Configuration configuration = new DefaultConfiguration();
                final ConnectionProvider provider = new DefaultConnectionProvider(dataSource.getConnection());
                configuration.set(provider);
                /* Dialect selected */
                final SQLDialect dialect = Pool.DIALECT.get(database.getCategory());
                LOGGER.debug("[ ZERO ] Jooq Database ：Dialect = {0}, Database = {1}, ", dialect, database.toJson().encodePrettily());
                configuration.set(dialect);
                context = DSL.using(configuration);
            } catch (final SQLException ex) {
                // LOGGER.jvm(ex);
            }
        }
    }

    private void initJdbc() {
        if (null == dataSource) {
            /*
             * Very important here, here are unique pool of old code with singleton
             * this.dataSource = Ut.singleton(HikariDataSource.class);
             *
             * Fix Issue: IllegalState The configuration of the pool is sealed once started
             * Root Cause: Here we could not use singleton pool, must create new one of DataSource.
             * If you use singleton design pattern here, when you want to switch datasource or reuse
             * the old data source, above issue will re-produce.
             *
             * Here are some background: once HikariCPPool started, you should not change the jdbcUrl.
             * If you want to change the jdbcUrl, above exception will throw out.
             *
             * The pre-condition is that you have set jdbcUrl before, and the HikariCPPool did not
             * distinguish whether it's the same data source by `jdbcUrl`, instead by java reference.
             * If we used singleton design pattern here, it means that you get previous reference each time
             * `old == new` will be true, when you set jdbcUrl again, above issue will throw out.
             *
             */
            dataSource = new HikariDataSource();
            /*
             * Ignore driverClass after jdbc4
             */
            dataSource.setJdbcUrl(database.getJdbcUrl());
            dataSource.setUsername(database.getUsername());
            dataSource.setPassword(database.getPassword());
        }
    }

    private void initPool() {
        if (Objects.nonNull(database)) {
            // Default configuration
            dataSource.setAutoCommit(true);
            dataSource.setConnectionTimeout(30000L);
            dataSource.setIdleTimeout(600000L);
            dataSource.setMaxLifetime(25600000L);
            dataSource.setMinimumIdle(256);
            dataSource.setMaximumPoolSize(512);

            // Default attributes
            dataSource.addDataSourceProperty("cachePrepStmts", "true");
            dataSource.addDataSourceProperty("prepStmtCacheSize", "1024");
            dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            // Data pool name
            dataSource.setPoolName("ZERO-POOL-DATA");
        }
    }
}
