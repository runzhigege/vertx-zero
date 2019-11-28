package io.vertx.tp.optic;

import io.vertx.core.MultiMap;
import io.vertx.tp.error._501DataSourceException;
import io.vertx.tp.jet.atom.JtApp;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.tp.optic.environment.AmbientEnvironment;
import io.vertx.tp.plugin.database.DataPool;
import io.vertx.up.fn.Fn;

import java.util.Objects;

/*
 * Dynamic Data Source
 */
@SuppressWarnings("all")
public class DynamicDs implements DS {
    @Override
    public DataPool switchDs(final MultiMap headers) {
        /*
         * Get app
         */
        final JtApp app = Ambient.getCurrent(headers);
        /*
         * If app is not null
         */
        Fn.out(Objects.isNull(app), _501DataSourceException.class, this.getClass(), headers.toString());
        return this.getDs(app);
    }

    public DataPool switchDs(final String sigma) {
        final JtApp app = Ambient.getApp(sigma);
        /*
         * If app is not null
         */
        Fn.out(Objects.isNull(app), _501DataSourceException.class, this.getClass(), sigma);
        return this.getDs(app);
    }

    private DataPool getDs(final JtApp app) {
        /*
         * DataPool get here
         */
        final AmbientEnvironment env = Ambient.getEnvironments().get(app.getAppId());
        return env.getPool();
    }
}
