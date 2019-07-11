package io.vertx.tp.ambient.refine;

import cn.vertxup.ambient.domain.tables.pojos.XApp;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.up.atom.Database;
import io.vertx.up.log.Annal;
import org.jooq.DSLContext;

/*
 * Tool class available in current service only
 */
public class At {
    /*
     * Log
     */
    public static void infoInit(final Annal logger, final String pattern, final Object... args) {
        AtLog.infoInit(logger, pattern, args);
    }

    public static void infoEnv(final Annal logger, final String pattern, final Object... args) {
        AtLog.infoEnv(logger, pattern, args);
    }

    public static void infoFile(final Annal logger, final String pattern, final Object... args) {
        AtLog.infoFile(logger, pattern, args);
    }

    public static void infoApp(final Annal logger, final String pattern, final Object... args) {
        AtLog.infoApp(logger, pattern, args);
    }

    /*
     * App Info, Bind to new datasource or current get.
     */
    public static XApp app(final DSLContext context,
                           final String name) {
        return AtEnv.getApp(context, name);
    }

    public static XApp app(final String name) {
        return AtEnv.getApp(name);
    }

    public static Future<Database> databaseAsync(final String appId) {
        return AtEnv.getDatabaseWithCache(appId);
    }

    /*
     * File
     */
    public static JsonObject upload(final String category, final FileUpload fileUpload) {
        return AtEnv.upload(category, fileUpload);
    }

    public static JsonObject filters(final String appId, final String type, final String code) {
        return AtQuery.filters(appId, new JsonArray().add(type), code);
    }

    public static JsonObject filters(final String appId, final JsonArray types, final String code) {
        return AtQuery.filters(appId, types, code);
    }
}
