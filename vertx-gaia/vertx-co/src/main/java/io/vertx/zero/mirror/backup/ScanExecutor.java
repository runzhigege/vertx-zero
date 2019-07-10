package io.vertx.zero.mirror.backup;

import java.util.Set;
import java.util.function.Predicate;

public class ScanExecutor implements Scan {
    private volatile static ScanExecutor instance;

    private ScanExecutor() {
    }

    public static ScanExecutor getInstance() {
        if (instance == null) {
            synchronized (ScanExecutor.class) {
                if (instance == null) {
                    instance = new ScanExecutor();
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
