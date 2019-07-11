package io.vertx.quiz;

import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.tp.plugin.rpc.RpcInfix;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public abstract class QzMicro extends QzTc {

    @BeforeClass
    public static void setUp() {
        QzTc.setUp();
        /* Rpc Infix */
        RpcInfix.init(VERTX);
    }
}
