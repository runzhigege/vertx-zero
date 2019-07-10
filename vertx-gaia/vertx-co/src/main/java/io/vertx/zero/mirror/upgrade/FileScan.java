package io.vertx.zero.mirror.upgrade;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

class FileScan implements Scan {
    private String defaultClassPath = FileScan.class.getResource(".").getPath();

    FileScan(final String defaultClassPath) {
        this.defaultClassPath = defaultClassPath;
    }

    FileScan() {
    }

    void setDefaultClassPath(final String defaultClassPath) {
        this.defaultClassPath = defaultClassPath;
    }

    @Override
    public Set<Class<?>> search(final String packageName, final Predicate<Class<?>> predicate) {
        /*
         * Convert package name to path and get classpath
         */
        final String classpath = this.defaultClassPath;
        /*
         * Put our basPack convert to path name.
         */
        final String basePackPath = packageName.replace(".", File.separator);
        final String searchPath = classpath + basePackPath;
        return new ClassSearcher().doPath(new File(searchPath), packageName, predicate, true);
    }

    private static class ClassSearcher {
        private final Set<Class<?>> classPaths = new HashSet<>();

        private Set<Class<?>> doPath(final File file, String packageName, final Predicate<Class<?>> predicate, final boolean flag) {
            if (file.isDirectory()) {
                /*
                 * Folder to continue
                 */
                final File[] files = file.listFiles();
                if (!flag) {
                    packageName = packageName + "." + file.getName();
                }
                if (Objects.nonNull(files)) {
                    for (final File f1 : files) {
                        this.doPath(f1, packageName, predicate, false);
                    }
                }
            } else {
                /*
                 * Whether it's .class file
                 */
                if (file.getName().endsWith(CLASS_SUFFIX)) {
                    /*
                     * If it's class, put into our set here.
                     */
                    try {
                        /*
                         * Class name extraction
                         */
                        final Class<?> clazz = Thread
                                .currentThread().getContextClassLoader()
                                .loadClass(packageName + "." + file.getName().substring(0, file.getName().lastIndexOf(".")));
                        if (predicate == null || predicate.test(clazz)) {
                            this.classPaths.add(clazz);
                        }
                    } catch (final ClassNotFoundException e) {
                        // Nothing to do when could not found.
                    }
                }
            }
            return this.classPaths;
        }
    }
}
