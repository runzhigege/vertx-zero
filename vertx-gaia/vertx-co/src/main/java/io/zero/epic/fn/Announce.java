package io.zero.epic.fn;

import io.vertx.up.exception.WebException;
import io.vertx.up.log.Annal;
import io.vertx.zero.exception.UpException;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.ZeroRunException;
import io.zero.epic.Ut;

import java.util.function.Supplier;

/**
 * Announce means tell every one of Zero system that there occurs error, the error contains
 * 1. java.lang.Exception ( Checked )
 * 2. io.vertx.zero.exception.ZeroException ( Checked )
 * 3. java.lang.Throwable ( Runtime )
 * 4. io.vertx.zero.exception.ZeroRunException ( Runtime )
 */
class Announce {
    /**
     * Build zero error and throw ZeroException out.
     *
     * @param logger
     * @param zeroClass
     * @param args
     * @throws ZeroException
     */
    static void outZero(final Annal logger,
                        final Class<? extends ZeroException> zeroClass,
                        final Object... args)
            throws ZeroException {
        final ZeroException error = Ut.instance(zeroClass, args);
        if (null != error) {
            if (null != logger) {
                logger.zero(error);
            }
            throw error;
        }
    }

    /**
     * Build up error and throw ZeroRunException out.
     *
     * @param logger
     * @param upClass
     * @param args
     */
    static void outUp(final Annal logger,
                      final Class<? extends ZeroRunException> upClass,
                      final Object... args) {
        final ZeroRunException error = Ut.instance(upClass, args);
        if (null != error) {
            if (null != logger) {
                logger.vertx(error);
            }
            throw error;
        }
    }

    static void outWeb(final Annal logger,
                       final Class<? extends WebException> webClass,
                       final Object... args) {
        final WebException error = Ut.instance(webClass, args);
        if (null != error) {
            if (null != logger) {
                logger.warn(error.getMessage());
            }
            throw error;
        }
    }

    /*
     * New Structure to avoid Annal logger created.
     * Uniform method for out exception
     */
    static void out(final Class<?> errorCls, final Object... args) {
        if (UpException.class == errorCls.getSuperclass()) {
            final UpException error = Ut.instance(errorCls, args);
            if (null != error) {
                logError(error::getTarget, error::getMessage);
                throw error;
            }
        } else if (WebException.class == errorCls.getSuperclass()) {
            final WebException error = Ut.instance(errorCls, args);
            if (null != error) {
                logError(error::getTarget, error::getMessage);
                throw error;
            }
        }
    }

    static void outUp(final Class<? extends UpException> upClass,
                      final Object... args) {
        final UpException error = Ut.instance(upClass, args);
        if (null != error) {
            logError(error::getTarget, error::getMessage);
            throw error;
        }
    }

    static void outWeb(final Class<? extends WebException> webClass,
                       final Object... args) {
        final WebException error = Ut.instance(webClass, args);
        if (null != error) {
            logError(error::getTarget, error::getMessage);
            throw error;
        }
    }

    private static void logError(final Supplier<Class<?>> supplier,
                                 final Supplier<String> message) {
        final Class<?> target = supplier.get();
        if (null != target) {
            final Annal logger = Annal.get(target);
            logger.warn(message.get());
        }
    }

    static void toRun(final Annal logger,
                      final ZeroActuator actuator) {
        try {
            actuator.execute();
        } catch (final ZeroException ex) {
            if (null != logger) {
                logger.zero(ex);
            }
            throw new ZeroRunException(ex.getMessage()) {
            };
        } catch (final Throwable ex) {
            if (null != logger) {
                logger.jvm(ex);
            }
        }
    }

    /**
     * Execut actuator and throw ZeroRunException out
     *
     * @param actuator
     * @param logger
     */
    static void shuntRun(final Actuator actuator,
                         final Annal logger) {
        try {
            actuator.execute();
        } catch (final ZeroRunException ex) {
            if (null != logger) {
                logger.vertx(ex);
            }
            throw ex;
        } catch (final Throwable ex) {
            if (null != logger) {
                logger.jvm(ex);
            }
        }
    }
}
