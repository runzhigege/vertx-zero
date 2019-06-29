package io.vertx.tp.database;

import com.zaxxer.hikari.HikariDataSource;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Database;
import io.zero.epic.fn.Fn;
import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultConnectionProvider;

public class HikariDataPool implements DataPool {
    private static final Annal LOGGER = Annal.get(HikariDataPool.class);
    private final transient Database database;

    /* Each jdbc url has one Pool here **/
    private transient DSLContext context;
    private transient HikariDataSource dataSource;

    HikariDataPool(final Database database) {
        this.database = database;
        /*
         * Initializing data source
         */
        this.initJdbc();
        /*
         * Initializing data source pool
         */
        this.initPool();
        /*
         * Initializing data source of jooq
         */
        this.initJooq();
    }

    @Override
    public DSLContext getExecutor() {
        return this.context;
    }

    @Override
    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

    private void initJooq() {
        if (null == this.context) {
            Fn.safeJvm(() -> {
                // 初始化Jooq配置
                final Configuration configuration = new DefaultConfiguration();
                final ConnectionProvider provider = new DefaultConnectionProvider(this.dataSource.getConnection());
                configuration.set(provider);
                // 设置数据库方言
                final SQLDialect dialect = Pool.DIALECT.get(this.database.getCategory());
                LOGGER.debug("[ ZERO ] Jooq Database ：Dialect = {0}, Database = {1}, ", dialect, this.database.toJson().encodePrettily());
                configuration.set(dialect);
                this.context = DSL.using(configuration);
            });
        }
    }

    private void initJdbc() {
        if (null == this.dataSource) {
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
            this.dataSource = new HikariDataSource();
            /*
             * Ignore driverClass after jdbc4
             */
            this.dataSource.setJdbcUrl(this.database.getJdbcUrl());
            this.dataSource.setUsername(this.database.getUsername());
            this.dataSource.setPassword(this.database.getPassword());
        }
    }

    private void initPool() {
        // Default configuration
        this.dataSource.setAutoCommit(true);
        this.dataSource.setConnectionTimeout(30000L);
        this.dataSource.setIdleTimeout(600000L);
        this.dataSource.setMaxLifetime(25600000L);
        this.dataSource.setMinimumIdle(256);
        this.dataSource.setMaximumPoolSize(512);

        // Default attributes
        this.dataSource.addDataSourceProperty("cachePrepStmts", "true");
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", "1024");
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // Data pool name
        this.dataSource.setPoolName("ZERO-POOL-DATA");
    }
}
