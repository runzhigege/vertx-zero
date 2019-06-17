package io.zero.quiz;

import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.up.plugin.rpc.RpcInfix;
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
