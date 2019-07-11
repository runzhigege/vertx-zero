package io.vertx.tp.plugin.jooq;

import io.vertx.core.Vertx;
import io.vertx.tp.database.DataPool;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.log.Annal;
import io.vertx.up.plugin.Infix;
import io.vertx.up.exception.zero.JooqVertxNullException;
import io.vertx.up.util.Ut;
import io.vertx.up.fn.Fn;
import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultConnectionProvider;

import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin
@SuppressWarnings("unchecked")
public class JooqInfix implements Infix {

    private static final Annal LOGGER = Annal.get(JooqInfix.class);

    private static final String NAME = "ZERO_JOOQ_POOL";

    private static final ConcurrentMap<String, Configuration> CONFIGS
            = new ConcurrentHashMap<>();

    private static Vertx vertxRef;

    private static void initInternal(final Vertx vertx,
                                     final String name) {
        vertxRef = vertx;
        Fn.pool(CONFIGS, name,
                () -> Infix.init(Plugins.Infix.JOOQ,
                        (config) -> {
                            // Initialized client
                            final Configuration configuration = new DefaultConfiguration();
                            configuration.set(SQLDialect.MYSQL_5_7);
                            // Switch to Data Pool
                            final DataPool pool = DataPool.create();
                            final Connection connection = Fn.getJvm(() -> pool.getDataSource().getConnection());
                            // Database object it bind to jooq configuration
                            final ConnectionProvider provider = new DefaultConnectionProvider(connection);
                            // Initialized default configuration
                            configuration.set(provider);
                            return configuration;
                        }, JooqInfix.class));
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    public static <T> T getDao(final Class<T> clazz) {
        Fn.outUp(null == vertxRef, LOGGER,
                JooqVertxNullException.class, clazz);
        final T dao = Ut.instance(clazz, CONFIGS.get(NAME));
        Ut.invoke(dao, "setVertx", vertxRef);
        return dao;
    }

    public static DSLContext getDSL() {
        final Configuration configuration = CONFIGS.get(NAME);
        return configuration.dsl();
    }

    @Override
    public Configuration get() {
        return CONFIGS.get(NAME);
    }
}
