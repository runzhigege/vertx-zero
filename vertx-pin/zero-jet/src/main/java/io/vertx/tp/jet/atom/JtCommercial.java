package io.vertx.tp.jet.atom;

import cn.vertxup.jet.domain.tables.pojos.IService;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtKey;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.up.atom.Database;
import io.vertx.up.atom.Integration;
import io.vertx.up.commune.Commercial;
import io.vertx.up.eon.ID;
import io.vertx.up.eon.em.ChannelType;
import io.vertx.up.util.Ut;

import java.util.Objects;

/*
 * Another data structure for bridge
 * 1) Api + Service
 * 2) Job + Service
 *
 * It means that serviceComponent could be consumed by different entrance such as:
 * 1) Request-Response ( IApi )
 * 2) Publish-Subscribe ( IJob )
 */
@SuppressWarnings("unchecked")
public abstract class JtCommercial implements Commercial {
    private transient IService service;
    /*
     * Shared data structure for
     * 1) JtApp ( application data )
     */
    private transient JtApp app;
    private transient JtConfig config;

    JtCommercial() {
    }

    JtCommercial(final IService service) {
        this.service = service;
    }

    public <T extends JtCommercial> T bind(final JtConfig config) {
        this.config = config;
        return (T) this;
    }

    public <T extends JtCommercial> T bind(final String appId) {
        app = Ambient.getApp(appId);
        return (T) this;
    }

    /*
     * Public interface to return `IService` reference
     */
    public IService service() {
        return service;
    }

    /*
     * Sub class used method for some processing
     */
    protected JtApp getApp() {
        return app;
    }

    protected JtConfig getConfig() {
        return config;
    }

    /*
     * Each sub class must set implementation of this method here.
     */
    public abstract String key();

    @Override
    public ChannelType channelType() {
        return Ut.toEnum(service::getChannelType, ChannelType.class, ChannelType.ADAPTOR);
    }

    @Override
    public Class<?> channelComponent() {
        return Jt.toChannel(service::getChannelComponent, channelType());
    }

    @Override
    public Class<?> businessComponent() {
        return Ut.clazz(service.getServiceComponent());
    }

    @Override
    public Class<?> recordComponent() {
        return Ut.clazz(service.getServiceRecord());
    }

    @Override
    public Database database() {
        return Jt.toDatabase(service::getConfigDatabase, app.getSource());
    }

    @Override
    public Integration integration() {
        return Jt.toIntegration(service::getConfigIntegration);
    }

    @Override
    public String app() {
        return app.getAppId();
    }

    /*
     * Non - Interface method here.
     */
    public String identifier() {
        return service.getIdentifier();
    }

    // ---------- Basic Json
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JtUri)) {
            return false;
        }
        final JtUri jtUri = (JtUri) o;
        return key().equals(jtUri.key());
    }

    @Override
    public int hashCode() {
        return Objects.hash(key());
    }

    @Override
    public JsonObject toJson() {
        final JsonObject data = new JsonObject();
        /* key data */
        data.put(JtKey.Delivery.KEY, key());

        /* service, config */
        data.put(JtKey.Delivery.SERVICE, (JsonObject) Ut.serializeJson(service()));
        data.put(JtKey.Delivery.CONFIG, (JsonObject) Ut.serializeJson(config));

        /* appId */
        data.put(JtKey.Delivery.APP_ID, app.getAppId());
        /* Reflection */
        data.put(ID.CLASS, getClass().getName());
        return data;
    }

    @Override
    public void fromJson(final JsonObject data) {
        /*
         * service, config
         */
        service = Ut.deserialize(data.getJsonObject(JtKey.Delivery.SERVICE), IService.class);
        config = Ut.deserialize(data.getJsonObject(JtKey.Delivery.CONFIG), JtConfig.class);
        /*
         * application id
         */
        final String appId = data.getString(JtKey.Delivery.APP_ID);
        app = Ambient.getApp(appId);
    }
}
