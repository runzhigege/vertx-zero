package io.vertx.up.web.anima;

import io.reactivex.Observable;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.up.log.Annal;
import io.vertx.up.plugin.Infix;
import io.vertx.up.web.ZeroAmbient;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class InfixPlugin {

    private transient final Class<?> clazz;
    private transient final Annal logger;

    private InfixPlugin(final Class<?> clazz) {
        this.clazz = clazz;
        this.logger = Annal.get(clazz);
    }

    static InfixPlugin create(final Class<?> clazz) {
        return Fn.pool(Pool.PLUGINS, clazz, () -> new InfixPlugin(clazz));
    }

    void inject(final Object proxy) {
        final ConcurrentMap<Class<?>, Class<?>> binds = this.getBind();
        final Class<?> type = proxy.getClass();
        Observable.fromArray(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Plugin.class))
                .subscribe(field -> {
                    final Class<?> fieldType = field.getType();
                    final Class<?> infixCls = binds.get(fieldType);
                    if (null != infixCls) {
                        if (Instance.isMatch(infixCls, Infix.class)) {
                            final Infix reference = Instance.singleton(infixCls);
                            final Object tpRef = Instance.invoke(reference, "get");
                            final String fieldName = field.getName();
                            Instance.set(proxy, fieldName, tpRef);
                        } else {
                            this.logger.warn(Info.INFIX_IMPL, infixCls.getName(), Infix.class.getName());
                        }
                    } else {
                        this.logger.warn(Info.INFIX_NULL, "tp", field.getName(), type.getName());
                    }
                });
    }

    private ConcurrentMap<Class<?>, Class<?>> getBind() {
        // Extract all infixes
        final Set<Class<?>> infixes = new HashSet<>(ZeroAmbient.getInjections().values());
        final ConcurrentMap<Class<?>, Class<?>> binds = new ConcurrentHashMap<>();
        Observable.fromIterable(infixes)
                .filter(Infix.class::isAssignableFrom)
                .subscribe(item -> {
                    final Method method = Fn.getJvm(() -> item.getDeclaredMethod("get"), item);
                    final Class<?> type = method.getReturnType();
                    binds.put(type, item);
                });
        return binds;
    }
}
