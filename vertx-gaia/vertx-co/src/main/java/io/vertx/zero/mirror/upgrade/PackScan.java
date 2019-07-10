package io.vertx.zero.mirror.upgrade;

import java.util.Set;
import java.util.function.Predicate;

public class PackScan implements Scan {
    private volatile static PackScan instance;

    private PackScan() {
    }

    public static PackScan getInstance() {
        if (instance == null) {
            synchronized (PackScan.class) {
                if (instance == null) {
                    instance = new PackScan();
                }
            }
        }
        return instance;
    }

    @Override
    public Set<Class<?>> search(final String packageName, final Predicate<Class<?>> predicate) {
        final Scan fileSc = new FileScan();
        final Set<Class<?>> fileSearch = fileSc.search(packageName, predicate);
        final Scan jarScanner = new JarScan();
        final Set<Class<?>> jarSearch = jarScanner.search(packageName, predicate);
        fileSearch.addAll(jarSearch);
        return fileSearch;
    }
}
