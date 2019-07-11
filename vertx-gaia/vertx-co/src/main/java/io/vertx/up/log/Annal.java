package io.vertx.up.log;

import io.vertx.core.VertxException;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.zero.epic.Ut;
import io.vertx.zero.fn.Actuator;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.log.Log;
import io.vertx.zero.log.internal.Log4JAnnal;
import io.vertx.zero.runtime.ZeroAmbient;

import java.util.Set;

/**
 * Unite Logging system connect to vert.x, io.vertx.zero.io.vertx.zero.io.vertx.zero.epic kit of Vertx-Zero
 */
public interface Annal {

    static Annal get(final Class<?> clazz) {
        return new CommonAnnal(clazz);
    }

    /*
     * Re-invoked logging for executing, here are logger sure to
     * Avoid Null Pointer exception
     */
    static <T> void sure(final Annal logger, final Actuator actuator) {
        if (null != logger) {
            actuator.execute();
        }
    }

    void vertx(VertxException ex);

    void zero(ZeroException ex);

    void jvm(Throwable ex);

    void warn(String key, Object... args);

    void error(String key, Object... args);

    void info(String key, Object... args);

    void debug(String key, Object... args);
}

class CommonAnnal implements Annal {

    private static final Logger RECORD =
            LoggerFactory.getLogger(CommonAnnal.class);
    private static final Set<Class<?>> OUTED = new ConcurrentHashSet<>();

    private transient final Annal logger;

    CommonAnnal(final Class<?> clazz) {
        Class<?> inject = ZeroAmbient.getPlugin("logger");
        if (null == inject) {
            Log.debug(RECORD, Info.INF_INJECT, clazz);
            inject = Log4JAnnal.class;
        }
        if (!OUTED.contains(inject)) {
            Log.debug(RECORD, Info.INF_ANNAL, inject, clazz);
            OUTED.add(inject);
        }
        logger = Ut.instance(inject, clazz);
    }

    @Override
    public void warn(final String key, final Object... args) {
        logger.warn(key, args);
    }

    @Override
    public void error(final String key, final Object... args) {
        logger.error(key, args);
    }

    @Override
    public void vertx(final VertxException ex) {
        logger.vertx(ex);
    }

    @Override
    public void zero(final ZeroException ex) {
        logger.zero(ex);
    }

    @Override
    public void jvm(final Throwable ex) {
        logger.jvm(ex);
    }

    @Override
    public void info(final String key, final Object... args) {
        logger.info(key, args);
    }

    @Override
    public void debug(final String key, final Object... args) {
        logger.debug(key, args);
    }
}
