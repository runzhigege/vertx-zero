package cn.vertxup;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.tp.plugin.excel.ExcelInfix;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.tp.rbac.DataLoader;
import io.vertx.up.log.Annal;

public class AresLoader {

    public static void main(final String[] args) {
        /* Prepared */
        final VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(30000000000L);
        final Vertx vertx = Vertx.vertx(options);
        // Excel
        ExcelInfix.init(vertx);
        JooqInfix.init(vertx);
        /* ExcelClient */
        final ExcelClient client = ExcelInfix.getClient();
        client.loading("plugin/excel/data/data.demo.xlsx", handler -> {
            /* */
            final Annal LOGGER = Annal.get(DataLoader.class);
            LOGGER.info("[ Excel ] Successfully to finish loading !");
        });
    }
}
