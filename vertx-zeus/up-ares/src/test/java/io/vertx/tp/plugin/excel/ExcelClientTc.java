package io.vertx.tp.plugin.excel;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.TestContext;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.zero.quiz.ZeroBase;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExcelClientTc extends ZeroBase {
    @BeforeClass
    public static void setUp() {
        final VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(30000000000L);
        final Vertx vertx = Vertx.vertx(options);
        // Excel
        ExcelInfix.init(vertx);
        JooqInfix.init(vertx);
    }

    @Test
    public void test(final TestContext context) {
        final ExcelClient client = ExcelInfix.getClient();
        client.loading("plugin/excel/data/data.demo.xlsx", handler -> {

        });
    }
}
