package io.vertx.zero.mirror;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Package scan thread
 * This class extends from Thread for multi-threads scanning.
 */
class PackThread extends Thread {
    private final transient String pkg;
    private final transient Reflections reflections;
    private final Set<Class<?>> classes = new HashSet<>();

    PackThread(final String pkg) {
        /*
         * Set the name of current thread for monitoring.
         */
        this.setName("package-scanner-" + super.getId());
        this.pkg = pkg;
        this.reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(pkg))
                .setScanners(
                        new SubTypesScanner(false),
                        new ResourcesScanner()
                )
        );
    }

    @Override
    public void run() {
        /*
         * Get all types in current reflection of package.
         */
        final Set<String> names = this.reflections.getAllTypes();
        final Set<Class<?>> classSet = new HashSet<>();
        /*
         * Old version
         */
        // SCANNER.search(this.pkg, this.filter);
        // OldPackScanner.getClasses(this.filter, this.pkg);
        names.forEach(name -> {
            try {
                classSet.add(Thread
                        .currentThread().getContextClassLoader()
                        .loadClass(name));
            } catch (final Throwable ex) {
                // LOGGER.info(ex.getMessage());
            }
        });
        this.classes.addAll(classSet);
    }

    public Set<Class<?>> getClasses() {
        return this.classes;
    }
}
