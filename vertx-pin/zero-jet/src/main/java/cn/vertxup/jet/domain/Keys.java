/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.jet.domain;


import cn.vertxup.jet.domain.tables.IApi;
import cn.vertxup.jet.domain.tables.IJob;
import cn.vertxup.jet.domain.tables.IService;
import cn.vertxup.jet.domain.tables.records.IApiRecord;
import cn.vertxup.jet.domain.tables.records.IJobRecord;
import cn.vertxup.jet.domain.tables.records.IServiceRecord;

import javax.annotation.Generated;

import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>DB_ETERNAL</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<IApiRecord> KEY_I_API_PRIMARY = UniqueKeys0.KEY_I_API_PRIMARY;
    public static final UniqueKey<IApiRecord> KEY_I_API_URI = UniqueKeys0.KEY_I_API_URI;
    public static final UniqueKey<IJobRecord> KEY_I_JOB_PRIMARY = UniqueKeys0.KEY_I_JOB_PRIMARY;
    public static final UniqueKey<IJobRecord> KEY_I_JOB_NAMESPACE = UniqueKeys0.KEY_I_JOB_NAMESPACE;
    public static final UniqueKey<IJobRecord> KEY_I_JOB_SIGMA_2 = UniqueKeys0.KEY_I_JOB_SIGMA_2;
    public static final UniqueKey<IJobRecord> KEY_I_JOB_SIGMA = UniqueKeys0.KEY_I_JOB_SIGMA;
    public static final UniqueKey<IServiceRecord> KEY_I_SERVICE_PRIMARY = UniqueKeys0.KEY_I_SERVICE_PRIMARY;
    public static final UniqueKey<IServiceRecord> KEY_I_SERVICE_NAME = UniqueKeys0.KEY_I_SERVICE_NAME;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<IApiRecord> KEY_I_API_PRIMARY = Internal.createUniqueKey(IApi.I_API, "KEY_I_API_PRIMARY", IApi.I_API.KEY);
        public static final UniqueKey<IApiRecord> KEY_I_API_URI = Internal.createUniqueKey(IApi.I_API, "KEY_I_API_URI", IApi.I_API.URI, IApi.I_API.METHOD, IApi.I_API.SIGMA);
        public static final UniqueKey<IJobRecord> KEY_I_JOB_PRIMARY = Internal.createUniqueKey(IJob.I_JOB, "KEY_I_JOB_PRIMARY", IJob.I_JOB.KEY);
        public static final UniqueKey<IJobRecord> KEY_I_JOB_NAMESPACE = Internal.createUniqueKey(IJob.I_JOB, "KEY_I_JOB_NAMESPACE", IJob.I_JOB.NAMESPACE, IJob.I_JOB.NAME);
        public static final UniqueKey<IJobRecord> KEY_I_JOB_SIGMA_2 = Internal.createUniqueKey(IJob.I_JOB, "KEY_I_JOB_SIGMA_2", IJob.I_JOB.SIGMA, IJob.I_JOB.NAME);
        public static final UniqueKey<IJobRecord> KEY_I_JOB_SIGMA = Internal.createUniqueKey(IJob.I_JOB, "KEY_I_JOB_SIGMA", IJob.I_JOB.SIGMA, IJob.I_JOB.CODE);
        public static final UniqueKey<IServiceRecord> KEY_I_SERVICE_PRIMARY = Internal.createUniqueKey(IService.I_SERVICE, "KEY_I_SERVICE_PRIMARY", IService.I_SERVICE.KEY);
        public static final UniqueKey<IServiceRecord> KEY_I_SERVICE_NAME = Internal.createUniqueKey(IService.I_SERVICE, "KEY_I_SERVICE_NAME", IService.I_SERVICE.NAME, IService.I_SERVICE.NAMESPACE);
    }
}
