package io.vertx.tp.crud.init;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.crud.cv.IxFolder;
import io.vertx.tp.crud.cv.IxMsg;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;
import io.vertx.up.eon.FileSuffix;
import io.vertx.up.eon.Strings;
import io.vertx.up.epic.Ut;
import io.vertx.up.fn.Fn;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Dao class initialization
 * plugin/crud/module/ folder singleton
 */
class IxDao {
    /*
     * Logger for IxDao
     */
    private static final Annal LOGGER = Annal.get(IxDao.class);

    private static final ConcurrentMap<String, IxModule> CONFIG_MAP =
            new ConcurrentHashMap<>();

    static void init() {
        /*
         * Read all definition files, wall files must be following:
         * <name>.json
         * 1）Each file could define only one module, the filename is module name.
         * 2）Each file must be json format with .json extension, others will be ignored.
         * */
        final List<String> files = Ut.ioFiles(IxFolder.MODULE, FileSuffix.JSON);

        files.forEach(file -> {
            /* 1.File absolute path under classpath */
            final String path = IxFolder.MODULE + file;
            final JsonObject configDao = Ut.ioJObject(path);

            Fn.safeNull(() -> {
                /* 2. Deserialize to IxConfig object */
                final IxModule config = Ut.deserialize(configDao, IxModule.class);
                /* 3. Processed key */
                final String key = file.replace(Strings.DOT + FileSuffix.JSON, Strings.EMPTY);
                if (file.contains(config.getName())) {
                    /* 4. Logger */
                    Ix.infoInit(LOGGER, IxMsg.INIT_INFO, path, key);
                    /*
                     * Resolution for resource key calculation
                     */
                    IxConfiguration.addUrs(key);
                    CONFIG_MAP.put(key, config);
                } else {
                    Ix.errorInit(LOGGER, IxMsg.INIT_ERROR, path, config.getName());
                }
            }, configDao);
        });
        Ix.infoInit(LOGGER, "IxDao Finished ! Size = {0}, Uris = {0}",
                CONFIG_MAP.size(), IxConfiguration.getUris().size());
    }

    static IxModule get(final String actor) {
        Ix.infoRest(LOGGER, "Actor = {0}", actor);
        final IxModule config = CONFIG_MAP.get(actor);
        return Fn.getNull(null, () -> config, config);
    }

    static UxJooq get(final IxModule config) {
        return Fn.getNull(null, () -> {
            final Class<?> daoCls = config.getDaoCls();
            assert null != daoCls : " Should not be null, check configuration";
            /* 1. Build UxJooq Object */
            final UxJooq dao = Ux.Jooq.on(daoCls);
            final String pojo = config.getPojo();

            /* 2. Where existing pojo.yml ( Zero support yml file to define mapping ) */
            if (Ut.notNil(pojo)) {
                dao.on(pojo);
            }
            return dao;
        }, config);
    }
}
