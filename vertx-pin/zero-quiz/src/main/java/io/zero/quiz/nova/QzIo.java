package io.zero.quiz.nova;

import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.FileSuffix;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;
import io.zero.quiz.cv.QzApi;

class QzIo {

    static String ioFile(final Class<?> clazz, final String input) {
        final String filename = clazz.getPackage().getName() + '/' + input;
        final Annal logger = Annal.get(clazz);
        logger.info("[ ZERO Qz ] Input File: {0}", filename);
        return filename + Strings.DOT + FileSuffix.JSON;
    }

    static QzApi getApi(final JsonObject input) {
        final String apiName = input.getString(QzApi.class.getSimpleName());
        return Fn.getNull(QzApi.DEFINED, () -> {
            final QzApi api = Ut.toEnum(QzApi.class, apiName);
            return Fn.getNull(QzApi.DEFINED, () -> api, api);
        }, apiName);
    }
}
