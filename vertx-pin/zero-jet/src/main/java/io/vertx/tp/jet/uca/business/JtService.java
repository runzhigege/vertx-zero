package io.vertx.tp.jet.uca.business;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Diode;
import io.vertx.up.commune.config.Adminicle;

/*
 * 统一接口
 * AbstractJob / AbstractComponent
 * 统一接口处理方法
 *
 * 「Tree Structure」
 * Component:
 * |- Adaptor: AdaptorComponent ( database )
 * ---- | - Director: AbstractDirector ( database, mission )
 * ---- | - Connector: AbstractConnector ( database, integration )
 * -------- | - Actor: AbstractActor ( database, integration, mission )
 * |- Job: AbstractJob ( database )
 * ---- | - AbstractIncome
 * ---- | - AbstractOutcome
 */
public interface JtService {
    /*
     * `serviceConfig` ( SERVICE_CONFIG ) of I_SERVICE
     */
    JsonObject options();

    /*
     * `configAdminicle` ( CONFIG_ADMINICLE ) of I_SERVICE
     */
    Adminicle adminicle();

    /*
     * `serviceConfig -> mapping` Mapping configuration
     */
    Diode mapping();
}
