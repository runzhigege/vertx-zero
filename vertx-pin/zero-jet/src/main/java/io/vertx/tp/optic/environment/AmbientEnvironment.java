package io.vertx.tp.optic.environment;

import cn.vertxup.jet.tables.daos.IApiDao;
import cn.vertxup.jet.tables.daos.IServiceDao;
import cn.vertxup.jet.tables.pojos.IApi;
import cn.vertxup.jet.tables.pojos.IService;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.tp.database.ZPool;
import io.vertx.tp.jet.atom.JtApp;
import io.vertx.tp.jet.atom.JtUri;
import io.zero.epic.fn.Fn;
import org.jooq.Configuration;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Cross Data Object
 * 1) App / Source information here
 * 2) Pool ( DSLContext, Connection, DataSource )
 */
public class AmbientEnvironment {
    /* Pool of JtUri */
    private final transient ConcurrentMap<String, JtUri> uris
            = new ConcurrentHashMap<>();
    /* XApp application, class JtApp */
    private final transient JtApp app;
    /* Data source, DSLContext, DataSource */
    private final transient ZPool pool;

    /*
     * IApiDao and IServiceDao
     */
    private final transient IApiDao apiDao;
    private final transient IServiceDao serviceDao;

    /*
     * Could not initialized outside package
     */
    AmbientEnvironment(final JtApp app) {
        /* Reference of application */
        this.app = app;
        /* ZPool created by Database */
        this.pool = ZPool.create(app.getSource());
        /* IApi / IService Extracting from database */
        {
            final Configuration configuration = this.pool.getExecutor().configuration();
            this.apiDao = new IApiDao(configuration);
            this.serviceDao = new IServiceDao(configuration);
        }
    }

    @Fluent
    public AmbientEnvironment init() {
        /* Secondary initialization: I_API & I_SERVICE */
        final List<IApi> apiList = this.apiDao.fetchBySigma(this.app.getSigma());
        final List<IService> serviceList = this.serviceDao.fetchBySigma(this.app.getSigma());

        if (this.uris.isEmpty()) {
            /*
             * Map for API + Service
             * serviceKey -> api
             * serviceKey -> service
             */
            final ConcurrentMap<String, IApi> apiMap = Fn.zipper(apiList, IApi::getServiceId, api -> api);
            final ConcurrentMap<String, IService> serviceMap = Fn.zipper(serviceList, IService::getKey, service -> service);

            /* Uri / Service Bind into data here */
            apiMap.keySet().stream()
                    .map(serviceId -> new JtUri(apiMap.get(serviceId), serviceMap.get(serviceId)))
                    .forEach(entry -> this.uris.put(entry.getKey(), entry));
        }
        return this;
    }

    public Connection getConnection() {
        return Fn.getJvm(() -> this.pool.getDataSource().getConnection(), this.pool);
    }
}
