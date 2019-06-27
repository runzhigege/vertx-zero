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

public class HikariZPool implements ZPool {
    private static final Annal LOGGER = Annal.get(HikariZPool.class);
    private final transient Database database;

    /* Each jdbc url has one Pool here **/
    private transient DSLContext context;
    private transient HikariDataSource dataSource;

    HikariZPool(final Database database) {
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
             * Very important here.
             * 唯一数据源，单件模式
             * this.dataSource = Ut.singleton(HikariDataSource.class);
             * Fix Issue: IllegalState The configuration of the pool is sealed once started
             * 问题来源：这里不可以使用单件模式，而是创建新的DataSource，原因很简单，如果使用了单间模式
             * 在切换数据源或者重新使用数据源的时候，会带来上述问题，问题根源是在于：HikariCPPool一旦
             * 启动过后，就不可以更改它本身的jdbcUrl，一旦更改，则会报出上述错误信息，但前提是某个已经设置
             * 过jdbcUrl的数据源，并且它并不是依赖jdbcUrl来判断是否同一数据源的，而是直接以引用为主，
             * 如果这个地方使用了单件，则不论任何时候创建的数据源都是唯一的，即引用相等，那么再设置jdbcUrl
             * 的时候就会抛出上述错误。
             * 在Origin X Engine的架构中，数据源的唯一性在上层由JdbcConnection来控制，而不是在这里控制
             * key = value的模式下，Origin X Engine会针对不同的jdbcUrl创建 OxPool实例，那么在这种
             * 情况下，是不会因为连接的建立创建多个数据源的。
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
