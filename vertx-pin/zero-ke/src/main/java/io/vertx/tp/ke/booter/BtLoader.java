package io.vertx.tp.ke.booter;

import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.tp.plugin.excel.ExcelInfix;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.List;

class BtLoader {

    static void importExcels(final String folder) {
        final List<String> files = Ut.ioFiles(folder);
        files.stream().filter(file -> !file.startsWith("~"))
                .map(file -> folder + file)
                .forEach(BtLoader::importExcel);
    }

    static void importExcel(final String filename) {
        /* ExcelClient */
        final ExcelClient client = ExcelInfix.getClient();
        /* Single file */
        client.loading(filename, handler -> {
            final Annal LOGGER = Annal.get(BtLoader.class);
            LOGGER.info("[ ZERO ] Successfully to finish loading ! data file = {0}", filename);
        });
    }
}
