package io.vertx.up.web.anima;

import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

class AffluxPlugin {

    private transient final Class<?> clazz;
    private transient final Annal logger;

    static AffluxPlugin create(final Class<?> clazz) {
        return Fn.pool(Pool.PLUGINS, clazz, () -> new AffluxPlugin(clazz));
    }

    private AffluxPlugin(final Class<?> clazz) {
        this.clazz = clazz;
        this.logger = Annal.get(clazz);
    }

    void inject(final Object proxy) {
        
    }
}
