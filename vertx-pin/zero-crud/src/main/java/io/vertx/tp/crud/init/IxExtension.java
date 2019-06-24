package io.vertx.tp.crud.init;

import io.vertx.tp.ke.extension.KeBus;
import io.vertx.tp.ke.extension.jooq.Apeak;
import io.vertx.tp.ke.extension.jooq.ApeakMy;
import io.vertx.tp.ke.extension.jooq.Seeker;
import io.vertx.tp.ke.tool.Ke;

class IxExtension {

    static Apeak getStub() {
        final Class<?> clazz = IxConfiguration.getConfig().getColumnComponent();
        return Ke.generate(clazz, () -> KeBus.epidemia(clazz));
    }

    static ApeakMy getMyStub() {
        final Class<?> clazz = IxConfiguration.getConfig().getColumnMyComponent();
        return Ke.generate(clazz, () -> KeBus.epidemiaMy(clazz));
    }

    static Seeker getSeeker() {
        final Class<?> clazz = IxConfiguration.getConfig().getSeekerComponent();
        return Ke.generate(clazz, () -> KeBus.seeker(clazz));
    }
}
