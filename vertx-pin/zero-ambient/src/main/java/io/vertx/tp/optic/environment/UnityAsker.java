package io.vertx.tp.optic.environment;

import cn.vertxup.ambient.tables.daos.XAppDao;
import cn.vertxup.ambient.tables.daos.XSourceDao;
import cn.vertxup.ambient.tables.pojos.XApp;
import cn.vertxup.ambient.tables.pojos.XSource;
import io.vertx.tp.ambient.cv.AtMsg;
import io.vertx.tp.ambient.refine.At;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;
import io.zero.epic.fn.Fn;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class UnityAsker {

    private static final Annal LOGGER = Annal.get(UnityAsker.class);

    private static final ConcurrentMap<String, XApp> APP_POOL =
            new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, XSource> SOURCE_POOL =
            new ConcurrentHashMap<>();

    static void init() {
        /* All app here */
        final List<XApp> applications = Ux.Jooq.on(XAppDao.class).findAll();
        At.infoApp(LOGGER, AtMsg.UNITY_APP, applications.size());
        final List<XSource> sources = Ux.Jooq.on(XSourceDao.class).findAll();
        At.infoApp(LOGGER, AtMsg.UNITY_SOURCE, sources.size());

        /* Data */
        APP_POOL.putAll(Fn.zipper(applications, XApp::getName, app -> app));
        SOURCE_POOL.putAll(Fn.zipper(sources, XSource::getAppId, source -> source));

        /* Key -> Sigma */
        UnityKey.ID_KEY.putAll(Fn.zipper(applications, XApp::getKey, XApp::getAppKey));
        UnityKey.KEY_ID.putAll(Fn.zipper(applications, XApp::getAppKey, XApp::getKey));

        UnityKey.ID_SIGMA.putAll(Fn.zipper(applications, XApp::getKey, XApp::getSigma));
        UnityKey.SIGMA_ID.putAll(Fn.zipper(applications, XApp::getSigma, XApp::getKey));

        UnityKey.KEY_SIGMA.putAll(Fn.zipper(applications, XApp::getKey, XApp::getSigma));
        UnityKey.SIGMA_KEY.putAll(Fn.zipper(applications, XApp::getSigma, XApp::getKey));
    }

    static ConcurrentMap<String, XApp> getApps() {
        return APP_POOL;
    }

    static ConcurrentMap<String, XSource> getSources() {
        return SOURCE_POOL;
    }
}
