package io.vertx.quiz;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.up.exception.ZeroException;
import io.vertx.up.uca.stable.Insurer;
import io.vertx.up.util.Ut;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public abstract class ZeroBase extends EpicBase {

    protected final static VertxOptions OPTIONS = /* Block issue of 2000ms for testing of long time works */
            new VertxOptions().setMaxEventLoopExecuteTime(30000000000L)
                    .setMaxWorkerExecuteTime(30000000000L)
                    .setBlockedThreadCheckInterval(10000);
    protected final static Vertx VERTX = Vertx.vertx(OPTIONS);
    @Rule
    public final RunTestOnContext rule = new RunTestOnContext(OPTIONS);

    public void ensure(final Class<?> insurerCls,
                       final JsonObject data, final JsonObject rule)
            throws ZeroException {
        final Insurer insurer
                = Ut.singleton(insurerCls);
        this.getLogger().info("[ ZERO Test ] Input data ( Object ): {0}", data);
        this.getLogger().info("[ ZERO Test ] Rule data: {0}", rule);
        insurer.flumen(data, rule);
    }

    public void ensure(final Class<?> insurerCls,
                       final JsonArray array, final JsonObject rule)
            throws ZeroException {
        final Insurer insurer
                = Ut.singleton(insurerCls);
        this.getLogger().info("[ ZERO Test ] Input data ( Array ): {0}", array);
        this.getLogger().info("[ ZERO Test ] Rule data: {0}", rule);
        insurer.flumen(array, rule);
    }
}
