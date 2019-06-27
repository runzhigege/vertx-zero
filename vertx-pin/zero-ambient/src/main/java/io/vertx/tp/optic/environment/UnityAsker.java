package io.vertx.tp.optic.environment;

import cn.vertxup.ambient.tables.daos.XAppDao;
import cn.vertxup.ambient.tables.daos.XSourceDao;
import cn.vertxup.ambient.tables.pojos.XApp;
import cn.vertxup.ambient.tables.pojos.XSource;
import io.vertx.tp.ambient.cv.AtMsg;
import io.vertx.tp.ambient.refine.At;
import io.vertx.tp.database.ZPool;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Database;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;
import org.jooq.Configuration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class UnityAsker {

    private static final Annal LOGGER = Annal.get(UnityAsker.class);

    private static final ConcurrentMap<String, XApp> APP_POOL =
            new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, XSource> SOURCE_POOL =
            new ConcurrentHashMap<>();

    /*
     * Be careful, in this step there is no Vert.x instance available, it means that you must
     * set Jooq environment before vert.x start up, in this situation, here you couldn't
     * use `Ux.Jooq.on(Dao.class)` mode to get Dao reference.
     */
    static void init() {
        /* All app here */
        final XAppDao appDao = Ut.singleton(XAppDao.class, getConfiguration());
        final List<XApp> applications = appDao.findAll();
        At.infoApp(LOGGER, AtMsg.UNITY_APP, applications.size());
        /* All data source here */
        final XSourceDao sourceDao = Ut.singleton(XSourceDao.class, getConfiguration());
        final List<XSource> sources = sourceDao.findAll();
        At.infoApp(LOGGER, AtMsg.UNITY_SOURCE, sources.size());

        /* Data, use application key as key here. */
        APP_POOL.putAll(Fn.zipper(applications, XApp::getKey, app -> app));
        SOURCE_POOL.putAll(Fn.zipper(sources, XSource::getAppId, source -> source));
    }

    private static Configuration getConfiguration() {
        final Database database = Database.getCurrent();
        final ZPool pool = ZPool.create(database);
        return pool.getExecutor().configuration();
    }

    static ConcurrentMap<String, XApp> getApps() {
        return APP_POOL;
    }

    static ConcurrentMap<String, XSource> getSources() {
        return SOURCE_POOL;
    }
}
