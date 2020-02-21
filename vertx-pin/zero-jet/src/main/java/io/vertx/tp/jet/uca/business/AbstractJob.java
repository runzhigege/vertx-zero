package io.vertx.tp.jet.uca.business;

import cn.vertxup.jet.domain.tables.pojos.IService;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.Service;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.commune.config.DualMapping;
import io.vertx.up.commune.config.Identity;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Abstract Service
 */
public abstract class AbstractJob implements Service {
    /*
     * The hash map to store dictionary data to connect
     * Income -> Job -> Outcome
     */
    private transient final ConcurrentMap<String, JsonArray> dictData
            = new ConcurrentHashMap<>();

    /*
     * The four reference source came from Service instance here
     * dict
     * - dictConfig
     * - dictComponent
     * - dictEpsilon
     *
     * identity
     * - identityComponent
     * - identity
     *
     * options
     * - serviceConfig
     *
     * mapping
     * - mappingConfig
     * - mappingMode
     * - mappingComponent
     */
    protected Dict dict() {
        return Jt.toDict(this.getService());
    }

    @Override
    public DualMapping mapping() {
        return Jt.toMapping(this.getService());
    }

    @Override
    public Identity identity() {
        return Jt.toIdentity(this.getService());
    }

    /*
     * Get `IService` reference here.
     */
    private IService getService() {
        final JsonObject metadata = this.mission().getMetadata();
        return Ut.deserialize(metadata.getJsonObject(KeField.SERVICE), IService.class);
    }

    /*
     * All `Job` sub-class must implement this method to get `Mission` object
     * This component configuration are all created by `Mission` instead of
     * channel @Contract.
     */
    abstract Mission mission();

    // ----------- Logger component --------------
    protected Annal logger() {
        return Annal.get(this.getClass());
    }
}
