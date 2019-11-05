package io.vertx.up.util;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import io.vertx.up.eon.Values;
import io.vertx.up.exception.zero.DuplicatedImplException;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.runtime.ZeroPack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked"})
final class Instance {

    private static final Annal LOGGER = Annal.get(Instance.class);

    private Instance() {
    }

    /**
     * Create new instance with reflection
     *
     * @param clazz  the instance type that you want to create
     * @param params constructor parameters of this type
     * @param <T>    Returned instance type
     * @return return new created instance
     */

    static <T> T instance(final Class<?> clazz,
                          final Object... params) {
        final Object created = Fn.getJvm(
                () -> construct(clazz, params), clazz);
        return Fn.getJvm(() -> (T) created, created);
    }

    /**
     * Generic type
     */
    static Class<?> genericT(final Class<?> target) {
        return Fn.getJvm(() -> {
            final Type type = target.getGenericSuperclass();
            return (Class) (((ParameterizedType) type).getActualTypeArguments()[0]);
        }, target);
    }

    /**
     * Singleton instances
     */
    static <T> T singleton(final Class<?> clazz,
                           final Object... params) {
        final Object created = Fn.pool(Storage.SINGLETON, clazz.getName(),
                () -> instance(clazz, params));
        // Must reference to created first.
        return Fn.getJvm(() -> (T) created, created);
    }

    /**
     * Get class from name, cached into memory pool
     *
     * @param name class Name
     * @return Class<?>
     */
    static Class<?> clazz(final String name) {
        return Fn.pool(Storage.CLASSES, name,
                () -> Fn.getJvm(() -> Thread.currentThread()
                        .getContextClassLoader()
                        .loadClass(name), name));
    }

    static Class<?> clazz(final String name, final Class<?> defaultCls) {
        if (Ut.isNil(name)) {
            return defaultCls;
        } else {
            final Class<?> clazz = clazz(name);
            if (Objects.isNull(clazz)) {
                return defaultCls;
            } else {
                return clazz;
            }
        }
    }

    /**
     * Check whether clazz implement the interfaceCls
     *
     * @param clazz        classname
     * @param interfaceCls interface name that will be implement
     * @return whether OK here
     */
    @SuppressWarnings("all")
    static boolean isMatch(final Class<?> clazz, final Class<?> interfaceCls) {
        final Class<?>[] interfaces = clazz.getInterfaces();
        boolean match = Arrays.stream(interfaces)
                .anyMatch(item -> item.equals(interfaceCls));
        if (!match) {
            /* continue to check parent */
            if (Objects.nonNull(clazz.getSuperclass())) {
                match = isMatch(clazz.getSuperclass(), interfaceCls);
            }
        }
        return match;
    }

    static <T> T getProxy(
            final Method method) {
        final Class<?> interfaceCls = method.getDeclaringClass();
        return Invoker.getProxy(interfaceCls);
    }

    /**
     * Whether the class contains no-arg constructor
     */
    static boolean noarg(final Class<?> clazz) {
        return Fn.getNull(Boolean.FALSE, () -> {
            final Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            boolean noarg = false;
            for (final Constructor<?> constructor : constructors) {
                if (0 == constructor.getParameterTypes().length) {
                    noarg = true;
                }
            }
            return noarg;
        }, clazz);
    }

    /**
     * Find the unique implementation for interfaceCls
     */
    static Class<?> uniqueChild(final Class<?> interfaceCls) {
        return Fn.getNull(null, () -> {
            final Set<Class<?>> classes = ZeroPack.getClasses();
            final List<Class<?>> filtered = classes.stream()
                    .filter(item -> interfaceCls.isAssignableFrom(item)
                            && item != interfaceCls)
                    .collect(Collectors.toList());
            final int size = filtered.size();
            // Non-Unique throw error out.
            Fn.outUp(Values.ONE < size, LOGGER,
                    DuplicatedImplException.class,
                    Instance.class, interfaceCls);
            // Null means direct interface only.
            return Values.ONE == size ? filtered.get(Values.IDX) : null;
        }, interfaceCls);
    }

    private static <T> T construct(final Class<?> clazz,
                                   final Object... params) {
        return Fn.getJvm(() -> {
            T ret = null;
            final Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            // ZeroPack all constructors
            for (final Constructor<?> constructor : constructors) {
                // Fast to construct
                if (0 == params.length) {
                    ret = (T) construct(clazz);
                }
                // Fast to compare argument length
                if (params.length != constructor.getParameterTypes().length) {
                    continue;
                }
                // The slowest construct
                final Object reference = constructor.newInstance(params);
                ret = Fn.getJvm(() -> ((T) reference), reference);
            }
            return ret;
        }, clazz, params);
    }

    private static <T> T construct(final Class<T> clazz) {
        return Fn.getJvm(() -> {
            // Reflect Asm
            final ConstructorAccess<T> access = ConstructorAccess.get(clazz);
            return access.newInstance();
        }, clazz);
    }
}
