package io.vertx.zero.mirror.upgrade;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarScan implements Scan {

    @Override
    public Set<Class<?>> search(final String packageName, final Predicate<Class<?>> predicate) {

        final Set<Class<?>> classes = new HashSet<>();

        try {
            /*
             * Get url of current thread.
             */
            final Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
            while (urlEnumeration.hasMoreElements()) {
                /*
                 * The result maybe: jar:file:/C:/Users/ibm/.m2/repository/junit/junit/4.12/junit-4.12.jar!/org/junit
                 */
                final URL url = urlEnumeration.nextElement();
                /*
                 * jar here
                 */
                final String protocol = url.getProtocol();
                if ("jar".equalsIgnoreCase(protocol)) {
                    /*
                     * Convert to jar connection
                     */
                    final JarURLConnection connection = (JarURLConnection) url.openConnection();
                    if (connection != null) {
                        final JarFile jarFile = connection.getJarFile();
                        if (jarFile != null) {

                            final Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                            while (jarEntryEnumeration.hasMoreElements()) {
                                /* just like:
                                 * org/
                                 * org/junit/
                                 * org/junit/rules/
                                 * org/junit/runners/*/
                                final JarEntry entry = jarEntryEnumeration.nextElement();
                                final String jarEntryName = entry.getName();
                                /*
                                 * Here should do some filters that does not exist in basePack
                                 */
                                if (jarEntryName.contains(".class") && jarEntryName.replaceAll("/", ".").startsWith(packageName)) {
                                    final String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                    final Class cls = Class.forName(className);
                                    if (predicate == null || predicate.test(cls)) {
                                        classes.add(cls);
                                    }
                                }
                            }
                        }
                    }
                } else if ("file".equalsIgnoreCase(protocol)) {
                    /*
                     * From maven sub-project instead of other.
                     */
                    final FileScan fileScanner = new FileScan();
                    fileScanner.setDefaultClassPath(url.getPath().replace(packageName.replace(".", "/"), ""));
                    classes.addAll(fileScanner.search(packageName, predicate));
                }
            }
        } catch (final ClassNotFoundException | IOException e) {
            // Nothing to do
        }
        return classes;
    }
}
