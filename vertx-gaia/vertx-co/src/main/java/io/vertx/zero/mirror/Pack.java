package io.vertx.zero.mirror;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Pack the package to extract classes.
 */
public final class Pack {

    private static final Annal LOGGER = Annal.get(Pack.class);

    private static final Set<Class<?>> CLASSES = new ConcurrentHashSet<>();

    private Pack() {
    }

    public static Set<Class<?>> getClasses() {
        return getClasses(null);
    }

    public static Set<Class<?>> getClasses(final Predicate<Class<?>> filter) {
        /*
         * Get all packages that will be scanned.
         */
        if (CLASSES.isEmpty()) {
            final Set<String> packageDirs = PackHunter.getPackages();
            packageDirs.add(Strings.DOT);
            CLASSES.addAll(multiClasses(packageDirs.toArray(new String[]{}), filter));
            LOGGER.info(Info.CLASSES, String.valueOf(CLASSES.size()));
        }
        return CLASSES;
    }

    private static Set<Class<?>> multiClasses(
            final String[] packageDir,
            final Predicate<Class<?>> filter) {
        // Counter
        final Set<PackThread> references = new HashSet<>();
        final Disposable disposable = Observable.fromArray(packageDir)
                .map(item -> new PackThread(item, filter))
                .map(item -> Ut.addThen(references, item))
                .subscribe(Thread::start);

        // Wait
        final Set<Class<?>> result = new HashSet<>();
        try {
            for (final PackThread item : references) {
                item.join();
            }
            // Collect all the classes
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
