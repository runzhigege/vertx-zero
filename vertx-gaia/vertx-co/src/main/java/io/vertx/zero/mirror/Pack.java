package io.vertx.zero.mirror;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.json.JsonArray;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Pack the package to extract classes.
 */
public final class Pack {

    private static final Annal LOGGER = Annal.get(Pack.class);

    private static final Set<Class<?>> CLASSES = new ConcurrentHashSet<>();

    private Pack() {
    }

    public static Set<Class<?>> getClasses() {
        /*
         * Get all packages that will be scanned.
         */
        if (CLASSES.isEmpty()) {
            final Set<String> packageDirs = PackHunter.getPackages();
            packageDirs.add(Strings.DOT);
            /*
             * Debug in package
             * Here I have tested package in total when development & production environment both.
             * The scanned package count are the same, it means that
             * here is no error when capture package here.
             * The left thing is that we should be sure class counter are the same as also.
             * 1) Current project classes
             * 2) For zero extension module, we also should add dependency classes into result.
             */
            CLASSES.addAll(multiClasses(packageDirs.toArray(new String[]{})));
            LOGGER.info(Info.CLASSES, String.valueOf(CLASSES.size()));
            /*
             * Debug in file
             */
            final Set<String> classSet = new TreeSet<>();
            CLASSES.forEach(clazz -> classSet.add(clazz.getName()));
            final JsonArray debugPkg = new JsonArray();
            classSet.forEach(debugPkg::add);
            Ut.ioOut("/Users/lang/Out/out.json", debugPkg);
        }
        return CLASSES;
    }

    /*
     * Multi thread class scanned for split packages instead of
     * single thread scanning.
     * It's turning performance for scanner.
     */
    private static Set<Class<?>> multiClasses(
            final String[] packageDir) {
        /*
         * Counter of all references of `PackThread`
         */
        final Set<PackThread> references = new HashSet<>();
        final Disposable disposable = Observable.fromArray(packageDir)
                .map(PackThread::new)
                .map(item -> Ut.addThen(references, item))
                .subscribe(Thread::start);

        /*
         * Main thread wait for sub-threads scanned results.
         */
        final Set<Class<?>> result = new HashSet<>();
        try {
            for (final PackThread item : references) {
                item.join();
            }
            /*
             * Collect all results and put into single set.
             */
            for (final PackThread thread : references) {
                result.addAll(thread.getClasses());
            }
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        } catch (final InterruptedException ex) {
            LOGGER.jvm(ex);
        } catch (final Throwable ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
