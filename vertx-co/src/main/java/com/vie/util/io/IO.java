package com.vie.util.io;

import com.vie.cv.Values;
import com.vie.hoc.HBool;
import com.vie.hoc.HFail;
import com.vie.hoc.HTry;
import com.vie.hors.ke.EmptyStreamException;
import com.vie.hors.ke.JsonFormatException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * The library for IO resource reading.
 */
public final class IO {
    /**
     * Read to JsonArray
     *
     * @param filename
     * @return
     */
    public static JsonArray getJArray(final String filename) {
        return HTry.exec(() -> new JsonArray(getString(filename)),
                (ex) -> new JsonFormatException(filename, ex));
    }

    /**
     * Read to JsonObject
     *
     * @param filename
     * @return
     */
    public static JsonObject getJObject(final String filename) {
        final String content = getString(filename);
        System.out.println(content);
        return HTry.exec(() -> new JsonObject(getString(filename)),
                (ex) -> new JsonFormatException(filename, ex));
    }

    /**
     * Read to String
     *
     * @param in
     * @return
     */
    public static String getString(final InputStream in) {
        final StringBuilder buffer = new StringBuilder(Values.BUFFER_SIZE);
        return HFail.exec(() -> {
            final BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(in, Values.ENCODING)
                    );
            // Character stream
            String line;
            while (null != (line = reader.readLine())) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        }, in);
    }

    public static String getString(final String filename) {
        return HFail.exec(
                () -> getString(Stream.read(filename)), filename);
    }

    /**
     * Read to property object
     *
     * @param filename
     * @return
     */
    public static Properties getProp(final String filename) {
        return HFail.exec(() -> {
            final Properties prop = new Properties();
            final InputStream in = Stream.read(filename);
            prop.load(in);
            in.close();
            return prop;
        }, filename);
    }

    /**
     * Read to URL
     *
     * @param filename
     * @return
     */
    public static URL getURL(final String filename) {
        return HFail.exec(() -> {
            final URL url = Thread.currentThread().getContextClassLoader()
                    .getResource(filename);
            return HBool.execTrue(null == url,
                    () -> IO.class.getResource(filename));
        }, filename);
    }

    /**
     * Read to File
     *
     * @param filename
     * @return
     */
    public static File getFile(final String filename) {
        return HFail.exec(() -> {
            final File file = new File(filename);
            return HBool.exec(file.exists(),
                    () -> file,
                    () -> {
                        final URL url = getURL(filename);
                        return HBool.exec(null != url,
                                () -> new File(url.getFile()),
                                new EmptyStreamException(filename));
                    });
        }, filename);
    }

    private IO() {
    }
}
