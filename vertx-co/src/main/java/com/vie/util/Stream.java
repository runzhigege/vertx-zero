package com.vie.util;

import com.vie.hoc.FnBool;
import com.vie.hoc.FnFail;
import com.vie.hors.ke.EmptyStreamException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Stream read class.
 */
public class Stream {
    /**
     * Direct read by vert.x logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(Stream.class);

    /**
     * @param filename
     * @return
     */
    public static InputStream read(final String filename) {
        return read(filename, null);
    }

    /**
     * Ensure read from path
     * 1. Read from current folder
     * 2. clazz == null: Read from class loader
     * 3. clazz != null: Read from clazz's class loader
     *
     * @param filename
     * @param clazz
     * @return
     */
    public static InputStream read(final String filename,
                                   final Class<?> clazz) {
        final File file = new File(filename);
        final InputStream in = FnBool.exec(file.exists(),
                () -> in(file),
                () -> FnBool.exec(null == clazz,
                        () -> in(filename),
                        () -> in(filename, clazz)));
        Log.info(LOGGER, Message.INF_PATH, filename, in);
        return FnBool.exec(null != in,
                () -> in,
                new EmptyStreamException(filename));
    }

    /**
     * Stream read from file object
     * new FileInputStream(file)
     *
     * @param file
     * @return
     */
    public static InputStream in(final File file) {
        return FnFail.exec(
                () -> FnBool.execTrue(file.exists() && file.isFile(),
                        () -> new FileInputStream(file)), file);
    }

    /**
     * Stream read from clazz
     * clazz.getResourceAsStream(filename)
     *
     * @param filename
     * @param clazz
     * @return
     */
    public static InputStream in(final String filename,
                                 final Class<?> clazz) {
        return FnFail.exec(
                () -> clazz.getResourceAsStream(filename), clazz, filename);
    }

    /**
     * Stream read from Thread Class Loader
     * Thread.currentThread().getContextClassLoader()
     *
     * @param filename
     * @return
     */
    public static InputStream in(final String filename) {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return FnFail.exec(
                () -> loader.getResourceAsStream(filename), filename);
    }
}
