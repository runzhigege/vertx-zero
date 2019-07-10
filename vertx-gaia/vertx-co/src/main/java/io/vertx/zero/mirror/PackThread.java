package io.vertx.zero.mirror;

import io.vertx.zero.mirror.previous.PackScan;

import java.util.HashSet;
import java.util.Set;

/**
 * Package scan thread
 * This class extends from Thread for multi-threads scanning.
 */
class PackThread extends Thread {
    private final transient String pkg;
    private final Set<Class<?>> classes = new HashSet<>();

    PackThread(final String pkg) {
        /*
         * Set the name of current thread for monitoring.
         */
        this.setName("package-scanner-" + super.getId());
        this.pkg = pkg;
    }

    @Override
    public void run() {
        /*
         * Scanned classes in current environment.
         */
        final Set<Class<?>> scanned = PackScan.getClasses(null, this.pkg);
        /*
         * Scanned classes with previous mode.
         */
        /* final Set<Class<?>> previous =
                PackScan.getClasses(null, this.pkg); */

        this.classes.addAll(scanned);
    }

    public Set<Class<?>> getClasses() {
        return this.classes;
    }
}
