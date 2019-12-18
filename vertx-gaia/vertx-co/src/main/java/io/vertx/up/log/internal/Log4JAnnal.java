package io.vertx.up.log.internal;

import io.vertx.core.VertxException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.up.log.Annal;
import io.vertx.up.log.Log;
import io.vertx.up.exception.ZeroException;

public class Log4JAnnal implements Annal {

    private transient final Logger logger;

    public Log4JAnnal(final Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void zero(final ZeroException ex) {
        Log.zero(logger, ex);
    }

    @Override
    public void warn(final String key, final Object... args) {
        Log.warn(logger, key, args);
    }

    @Override
    public void error(final String key, final Object... args) {
        Log.error(logger, key, args);
    }

    @Override
    public void vertx(final VertxException ex) {
        Log.vertx(logger, ex);
    }

    @Override
    public void jvm(final Throwable ex) {
        Log.jvm(logger, ex);
    }

    @Override
    public void info(final String key, final Object... args) {
        Log.info(logger, key, args);
    }

    @Override
    public void debug(final String key, final Object... args) {
        Log.debug(logger, key, args);
    }
}
