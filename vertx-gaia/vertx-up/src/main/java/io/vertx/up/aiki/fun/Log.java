package io.vertx.up.aiki.fun;

import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Ut;
import io.vertx.zero.eon.Strings;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Log {

    private final transient Annal logger;
    private transient String key;
    private static final ConcurrentMap<Integer, Annal> LOGGERS
            = new ConcurrentHashMap<>();

    private static Log INSTANCE;

    public static Log create(final Class<?> clazz) {
        if (null == INSTANCE) {
            INSTANCE = new Log(clazz);
        }
        return INSTANCE;
    }

    private Log(final Class<?> clazz) {
        this.logger = Fn.pool(LOGGERS, clazz.hashCode(), () -> Annal.get(clazz));
    }

    public Log on(final String key) {
        this.key = key;
        return this;
    }

    public Log info(final Object... args) {
        // Ready to output.
        if (Ut.isNil(this.key)) {
            final StringBuilder pattern = new StringBuilder();
            for (int idx = 0; idx < args.length; idx++) {
                pattern.append(Strings.LEFT_BRACES)
                        .append(idx)
                        .append(Strings.RIGHT_BRACES)
                        .append(" ");
            }
            this.logger.info(pattern.toString(), args);
        } else {
            this.logger.info(this.key, args);
        }
        // Clear
        this.key = null;
        return this;
    }
}
