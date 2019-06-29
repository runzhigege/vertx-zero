package io.zero.epic;

import io.vertx.up.log.Annal;
import io.zero.epic.fn.Fn;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("all")
class InstanceField {

    private static final Annal LOGGER = Annal.get(InstanceField.class);

    static <T> void set(final Object instance, final String name, final T value) {
        final Field field = Fn.getNull(() ->
                Fn.getJvm(() -> instance.getClass().getDeclaredField(name), LOGGER), instance, name);
        set(instance, field, value);
    }

    static <T> void set(final Object instance, final Field field, final T value) {
        Fn.safeNull(() -> Fn.safeJvm(() -> {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(instance, value);
        }, LOGGER), instance, field);
    }

    static Field[] fieldAll(final Object instance, final Class<?> fieldType) {
        final Function<Class<?>, Set<Field>> lookupFun = clazz -> lookUp(clazz, fieldType)
                .collect(Collectors.toSet());
        return Fn.getJvm(() -> fieldAll(instance.getClass(), fieldType).toArray(new Field[]{}),
                instance, fieldType);
    }

    private static Set<Field> fieldAll(final Class<?> clazz, final Class<?> fieldType) {
        final Set<Field> fieldSet = new HashSet<>();
        if (Object.class != clazz) {

            /* Self */
            fieldSet.addAll(lookUp(clazz, fieldType).collect(Collectors.toSet()));

            /* Parent Iterator */
            fieldSet.addAll(fieldAll(clazz.getSuperclass(), fieldType));
        }
        return fieldSet;
    }

    private static Field get(final Class<?> clazz,
                             final String name) {
        return Fn.getNull(() -> {
            if (clazz == Object.class) {
                return null;
            }
            final Field[] fields = clazz.getDeclaredFields();
            final Optional<Field> field = Arrays.stream(fields)
                    .filter(item -> name.equals(item.getName())).findFirst();
            if (field.isPresent()) {
                return field.get();
            } else {
                final Class<?> parentCls = clazz.getSuperclass();
                return get(parentCls, name);
            }
        }, clazz, name);
    }

    static <T> T getI(final Class<?> interfaceCls, final String name) {
        return Fn.getNull(() -> Fn.safeJvm(() -> {
                    final Field field = interfaceCls.getField(name);
                    final Object result = field.get(null);
                    if (null != result) {
                        return (T) result;
                    } else {
                        return null;
                    }
                }, LOGGER)
                , interfaceCls, name);
    }

    static <T> T get(final Object instance,
                     final String name) {
        return Fn.getNull(() -> Fn.safeJvm(() -> {
                    final Field field = get(instance.getClass(), name);
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    final Object result = field.get(instance);
                    if (null != result) {
                        return (T) result;
                    } else {
                        return null;
                    }
                }, LOGGER)
                , instance, name);
    }

    static Field[] fields(final Class<?> clazz) {
        final Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields)
                .filter(item -> !Modifier.isStatic(item.getModifiers()))
                .filter(item -> !Modifier.isAbstract(item.getModifiers()))
                .toArray(Field[]::new);
    }

    private static Stream<Field> lookUp(final Class<?> clazz, final Class<?> fieldType) {
        return Fn.getJvm(() -> {
            /* Lookup field */
            final Field[] fields = fields(clazz);
            /* Direct match */
            return Arrays.stream(fields)
                    .filter(field -> fieldType == field.getType() ||          // Direct match
                            fieldType == field.getType().getSuperclass() ||  // Super
                            Instance.isMatch(field.getType(), fieldType));
        });
    }
}
