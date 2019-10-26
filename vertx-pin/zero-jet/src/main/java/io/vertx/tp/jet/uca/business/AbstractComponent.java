package io.vertx.tp.jet.uca.business;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.Diode;
import io.vertx.up.commune.config.Adminicle;
import io.vertx.up.log.Annal;

/**
 * Four type components here, it is base class of
 * 「Tree Structure」
 * Component:
 * |- Adaptor: AdaptorComponent ( database )
 * ---- | - Director: AbstractDirector ( database, mission )
 * ---- | - Connector: AbstractConnector ( database, integration )
 * -------- | - Actor: AbstractActor ( database, integration, mission )
 * <p>
 * 「Not Recommend」
 * Here we do not recommend use this component directly.
 */
public abstract class AbstractComponent implements JtComponent, JtService {
    @Contract
    private transient JsonObject options;

    @Contract
    private transient Diode mapping;

    @Contract
    private transient Adminicle adminicle;

    /*
     * The logger of Annal here
     */
    protected Annal logger() {
        return Annal.get(this.getClass());
    }

    // -------------- 元数据配置部分 ------------------
    @Override
    public JsonObject options() {
        return this.options;
    }

    @Override
    public Adminicle adminicle() {
        return this.adminicle;
    }

    @Override
    public Diode mapping() {
        return this.mapping;
    }
}
