package io.vertx.tp.jet.uca.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._501NotImplementException;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.annotations.Contract;
import io.vertx.up.commune.ActIn;
import io.vertx.up.commune.ActOut;
import io.vertx.up.commune.Service;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.commune.config.DualMapping;
import io.vertx.up.commune.config.Identity;
import io.vertx.up.commune.config.XHeader;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._400SigmaMissingException;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.function.Function;

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
public abstract class AbstractComponent implements JtComponent, Service {

    // -------------- Metadata configuration ------------------

    /*
     * The four reference source came from `@Contract` injection here
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
    @Contract
    private transient JsonObject options;

    @Contract
    private transient Identity identity;

    @Contract
    private transient DualMapping mapping;

    @Contract
    private transient Dict dict;

    @Contract
    private transient XHeader header;  // Came from request

    /*
     * The logger of Annal here
     */
    protected Annal logger() {
        return Annal.get(this.getClass());
    }

    /*
     * There are required attribute
     * {
     *     "name": "app name",
     *     "identifier": "current identifier"
     * }
     */
    @Override
    public JsonObject options() {
        return Objects.isNull(this.options) ? new JsonObject() : this.options.copy();
    }

    @Override
    public Dict dict() {
        return this.dict;
    }

    @Override
    public Identity identity() {
        return this.identity;
    }

    /* Limitation Usage */
    @Override
    public DualMapping mapping() {
        return this.mapping;
    }

    protected XHeader header() {
        return this.header;
    }

    /* Uniform tunnel */
    protected Future<ActOut> transferAsync(final ActIn request, final Function<String, Future<ActOut>> executor) {
        final String sigma = request.sigma();
        if (Ut.isNil(sigma)) {
            final WebException error = new _400SigmaMissingException(this.getClass());
            return ActOut.future(error);
        } else {
            return executor.apply(sigma);
        }
    }

    /*
     * Provide default implementation
     * 1) For standard usage, it should provide sub-class inherit structure.
     * 2) For standalone usage, it could be parent class as @Contract parent
     */
    @Override
    public Future<ActOut> transferAsync(final ActIn actIn) {
        final WebException error = new _501NotImplementException(this.getClass());
        return Future.failedFuture(error);
    }

    /*
     * Contract for uniform reference
     */
    public <T> void contract(final T instance) {
        if (Objects.nonNull(instance)) {
            Ut.contract(instance, JsonObject.class, this.options());
            Ut.contract(instance, Identity.class, this.identity());
            Ut.contract(instance, Dict.class, this.dict());
            Ut.contract(instance, DualMapping.class, this.mapping());
            Ut.contract(instance, XHeader.class, this.header());
        }
    }
}
