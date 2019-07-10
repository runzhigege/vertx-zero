package io.vertx.zero.mirror;

import io.vertx.zero.mirror.upgrade.PackScan;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Package scan thread
 * This class extends from Thread for multi-threads scanning.
 */
class PackThread extends Thread {

    private final static PackScan SCANNER = PackScan.getInstance();
    private final transient String pkg;
    private final transient Predicate<Class<?>> filter;
    private final Set<Class<?>> classes = new HashSet<>();

    PackThread(final String pkg,
               final Predicate<Class<?>> filter) {
        /*
         * Set the name of current thread for monitoring.
         */
        this.setName("package-scanner-" + super.getId());
        this.pkg = pkg;
        this.filter = filter;
    }

    @Override
    public void run() {
        final Set<Class<?>> clazzes = SCANNER.search(this.pkg, this.filter);
        // OldPackScanner.getClasses(this.filter, this.pkg);
        this.classes.addAll(clazzes);
    }

    public Set<Class<?>> getClasses() {
        return this.classes;
    }
}
