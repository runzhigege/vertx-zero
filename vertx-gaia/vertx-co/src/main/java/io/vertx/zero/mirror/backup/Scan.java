package io.vertx.zero.mirror.backup;

import java.util.Set;
import java.util.function.Predicate;

/*
 * The interface that will be triggered by package scanner
 */
interface Scan {
    /*
     * Class suffix for scanner
     */
    String CLASS_SUFFIX = ".class";

    Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate);

    default Set<Class<?>> search(final String packageName) {
        return this.search(packageName, null);
    }
}
