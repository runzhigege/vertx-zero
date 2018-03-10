package io.vertx.tp.qiy;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.qiy.api.QiyAuthorize;
import io.vertx.tp.qiy.api.QiyUpload;
import io.vertx.up.exception._401QiyTokenException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

public class QiyClientImpl implements QiyClient {

    private static final Annal LOGGER = Annal.get(QiyClientImpl.class);

    private transient final Vertx vertx;
    private transient QiyToken token;

    private transient final QiyAuthorize authorizeApi;

    QiyClientImpl(final Vertx vertx, final QiyToken token) {
        this.vertx = vertx;
        this.token = token;
        // Authorized Api Reference
        this.authorizeApi = token.getInitApi(QiyAuthorize.class);
    }

    @Override
    public QiyClient init(final JsonObject config) {
        // Refresh QiyToken
        LOGGER.info(Info.TOKEN_RECORD, config);
        this.token = QiyToken.create(config);
        return this;
    }

    @Override
    public QiyClient authorize(final Handler<AsyncResult<JsonObject>> handler) {
        QiyRepdor.handle(this.authorizeApi.authorize(this.token.getClientId(), this.token.getClientSecret()))
                .setHandler(res -> {
                    this.token.setToken(res.result());
                    handler.handle(Future.succeededFuture(res.result()));
                });
        return this;
    }

    @Override
    public QiyClient refreshToken(final String refreshToken,
                                  final Handler<AsyncResult<JsonObject>> handler) {
        QiyRepdor.handle(this.authorizeApi.refreshToken(this.token.getClientId(), refreshToken))
                .setHandler(res -> {
                    this.token.setToken(res.result());
                    handler.handle(Future.succeededFuture(res.result()));
                });
        return this;
    }

    @Override
    public QiyClient requestFile(final String fileType,
                                 final String fileSize,
                                 final Handler<AsyncResult<JsonObject>> handler) {
        // Check whether the token is valid
        Fn.flingWeb(null == this.token || !this.token.isValid(), LOGGER,
                _401QiyTokenException.class, this.getClass(), this.token.getClientId());
        // Request upload
        final QiyUpload uploadApi = this.token.getUpApi(QiyUpload.class);
        QiyRepdor.complete(uploadApi.requestUpload(fileType, fileSize, this.token.getAccessToken()))
                .setHandler(res -> handler.handle(Future.succeededFuture(res.result())));
        return this;
    }

    @Override
    public QiyClient upload(final String address,
                            final String size,
                            final String range,
                            final String fileId,
                            final char[] content) {

        return this;
    }
}
