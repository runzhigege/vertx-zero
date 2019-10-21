package io.vertx.tp.ambient.extension;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.atom.AtConfig;
import io.vertx.tp.ambient.cv.AtMsg;
import io.vertx.tp.ambient.init.AtPin;
import io.vertx.tp.ambient.refine.At;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.tp.plugin.excel.ExcelInfix;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Uson;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DatumInit implements Init {

    private static final Annal LOGGER = Annal.get(DatumInit.class);
    private static final AtConfig CONFIG = AtPin.getConfig();

    @Override
    public Function<JsonObject, Future<JsonObject>> apply() {
        return appJson -> {
            At.infoApp(LOGGER, AtMsg.INIT_DATUM, appJson.encode());
            return this.doLoading(appJson)
                    /* Extension */
                    .compose(this::doExtension);
        };
    }

    public Future<JsonObject> doExtension(final JsonObject appJson) {
        final Init loader = AtPin.getLoader();
        if (Objects.isNull(loader)) {
            return Ux.toFuture(appJson);
        } else {
            return loader.apply().apply(appJson);
        }
    }

    private Future<JsonObject> doLoading(final JsonObject appJson) {
        /* Datum Loading */
        final String dataFolder = CONFIG.getDataFolder();
        final List<String> files = Ut.ioFiles(dataFolder);
        /* List<Future> */
        final List<Future<JsonObject>> futures = files.stream()
                .filter(Ut::notNil)
                /* Remove temp file of Excel */
                .filter(file -> !file.startsWith("~$"))
                .map(file -> dataFolder + file)
                .map(this::doLoading)
                .collect(Collectors.toList());
        return Ux.thenCombine(futures)
                /* Stored each result */
                .compose(results -> Uson.create().append(KeField.RESULT, results)
                        .toFuture())
                .compose(results -> Ux.toFuture(this.result(results, appJson)));
    }

    private Future<JsonObject> doLoading(final String filename) {
        final Promise<JsonObject> promise = Promise.promise();
        /* ExcelClient */
        final ExcelClient client = ExcelInfix.getClient();
        client.loading(filename, result -> {
            At.infoApp(LOGGER, AtMsg.INIT_DATUM_EACH, filename);
            if (result.succeeded()) {
                promise.complete(Ke.Result.bool(filename, Boolean.TRUE));
            } else {
                promise.fail(result.cause());
            }
        });
        return promise.future();
    }

    @Override
    public JsonObject result(final JsonObject input, final JsonObject appJson) {
        /* Extract Failure Filename, No thing to do or */
        return appJson;
    }
}
