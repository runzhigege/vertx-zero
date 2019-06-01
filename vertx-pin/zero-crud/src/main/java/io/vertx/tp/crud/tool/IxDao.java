package io.vertx.tp.crud.tool;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.zero.eon.FileSuffix;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Dao Class
 * ke/module/（Dao definition files）
 */
class IxDao {
    /*
     * module -> clazz
     */
    private static final ConcurrentMap<String, IxConfig> CONFIG_MAP =
            new ConcurrentHashMap<>();

    static {
        /*
         * Read all definition files, wall files must be following:
         * <name>.json
         * 1）Each file could define only one module, the filename is module name.
         * 2）Each file must be json format with .json extension, others will be ignored.
         * */
        final List<String> files = Ut.ioFiles("ke/module/", FileSuffix.JSON);

        files.forEach(file -> {
            /* 1.File absolute path under classpath */
            final String path = "ke/module/" + file;
            final JsonObject configDao = Ut.ioJObject(path);

            Fn.safeNull(() -> {
                /* 2. Deserialize to IxConfig object */
                final IxConfig config = Ut.deserialize(configDao, IxConfig.class);
                CONFIG_MAP.put(file.replace(FileSuffix.JSON, Strings.EMPTY), config);
            }, configDao);
        });
    }

    static IxConfig get(final String module) {
        return CONFIG_MAP.get(module);
    }

    static UxJooq getDao(final String module) {
        final IxConfig config = CONFIG_MAP.get(module);
        return Fn.getNull(null, () -> {
            final Class<?> daoCls = config.getDaoCls();
            return Fn.getNull(null, () -> {
                /* 1. Build UxJooq Object */
                final UxJooq dao = Ux.Jooq.on(daoCls);
                final String pojo = config.getPojo();

                /* 2. Where existing pojo.yml ( Zero support yml file to define mapping ) */
                if (Ut.notNil(pojo)) {
                    dao.on(pojo);
                }
                return dao;
            }, daoCls);
        }, config);
    }
}
