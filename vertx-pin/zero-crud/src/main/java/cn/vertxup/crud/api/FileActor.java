package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.cv.IxMsg;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.error._500ExportingErrorException;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.up.unity.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._500InternalServerException;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;
import io.vertx.up.fn.Fn;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Queue
public class FileActor {

    private static final Annal LOGGER = Annal.get(FileActor.class);

    @Plugin
    private transient ExcelClient client;

    @Address(Addr.File.IMPORT)
    public Future<Envelop> importFile(final Envelop request) {
        /* Import data here for result */
        final String actor = Ux.getString(request);
        final String filename = Ux.getString1(request);
        /* IxConfig */
        final IxModule config = IxPin.getActor(actor);
        final Future<Envelop> future = Future.future();
        final File file = new File(filename);
        if (file.exists()) {
            Fn.safeJvm(() -> {
                final InputStream inputStream = new FileInputStream(file);
                client.importTable(config.getTable(), inputStream, handler -> {
                    Ix.infoDao(LOGGER, IxMsg.FILE_LOADED, filename);
                    future.complete(Envelop.success(Boolean.TRUE));
                });
            });
        } else {
            future.complete(Envelop.success(Boolean.FALSE));
        }
        return future;
    }

    @Address(Addr.File.EXPORT)
    public Future<Envelop> exportFile(final Envelop request) {
        /* Headers */
        final ConcurrentMap<String, String> exportedHeaders = new ConcurrentHashMap<>();
        /* Removed */
        final JsonArray removed = new JsonArray();
        /* Search full column and it will be used in another method */
        return Ix.create(getClass()).input(request).envelop((dao, config) -> Unity.fetchFull(dao, request, config)

                /* Column initialization */
                .compose(columns -> {
                    final Set<String> columnSet = new HashSet<>();
                    Ut.itJArray(columns, (column, index) -> {
                        /* Key */
                        final String columnKey = column.getString(IxPin.getColumnKey());
                        /* Name */
                        final String columnLabel = column.getString(IxPin.getColumnLabel());
                        if (Ut.notNil(columnKey) && Ut.notNil(columnLabel)) {
                            exportedHeaders.put(columnKey, columnLabel);
                            /* All columns */
                            columnSet.add(columnKey);
                        }
                    });
                    return Ux.toFuture(columnSet);
                })

                /* Column calculation */
                .compose(columnSet -> {
                    /* Expected columns */
                    final JsonArray expected = Ux.getArray2(request);
                    columnSet.removeAll(expected.stream()
                            .filter(Objects::nonNull)
                            .map(column -> (String) column)
                            .collect(Collectors.toSet()));
                    /* projection calculation */
                    return Ux.toFuture(Ut.toJArray(columnSet));
                })

                /* Projection calculation */
                .compose(projection -> {
                    /* Parameters Extraction */
                    final JsonObject body = Ux.getJson1(request);
                    body.put(Inquiry.KEY_PROJECTION, projection);
                    {
                        /* Removed will be used in future */
                        removed.addAll(projection);
                    }
                    /* Calculation for projection here */
                    return Ux.toFuture(body);
                })

                /* Verify */
                .compose(input -> IxActor.verify().bind(request).procAsync(input, config))

                /* Execution */
                .compose(params -> Ix.query(params, config).apply(dao))

                /* Data Exporting */
                .compose(data -> {
                    /* Data for ExTable */
                    final JsonArray dataArray = data.getJsonArray("list");
                    /* Left columns, removed useless column */
                    removed.stream().map(item -> (String) item).forEach(exportedHeaders::remove);

                    /* Combine and build data of excel */
                    return combineData(dataArray, exportedHeaders);
                })

                /* Final exporting her for excel download */
                .compose(data -> {
                    final String actor = Ux.getString(request);
                    return exportTable(actor, data);
                })
                .compose(buffer -> Envelop.success(buffer).toFuture())
        );
    }

    private Future<JsonArray> combineData(final JsonArray data, final ConcurrentMap<String, String> headers) {
        final JsonArray combined = new JsonArray();
        /* Header sequence */
        final List<String> columns = new ArrayList<>(headers.keySet());
        /* Header */
        final JsonArray header = new JsonArray();
        columns.forEach(column -> header.add(headers.get(column)));
        combined.add(header);

        /* Data Part */
        Ut.itJArray(data, (each, index) -> {
            final JsonArray row = new JsonArray();
            /* Data Part */
            columns.stream().map(each::getValue).forEach(row::add);

            combined.add(row);
        });
        return Ux.toFuture(combined);
    }

    private Future<Buffer> exportTable(final String identifier, final JsonArray data) {
        /* Excel Client */
        final Future<Buffer> future = Future.future();
        client.exportTable(identifier, data, handler -> {
            if (handler.succeeded()) {
                future.complete(handler.result());
            } else {
                final Throwable error = handler.cause();
                if (Objects.nonNull(error)) {
                    final WebException failure = new _500ExportingErrorException(getClass(), error.getMessage());
                    future.fail(failure);
                } else {
                    future.fail(new _500InternalServerException(getClass(), "Unexpected Error"));
                }
            }
        });
        return future;
    }
}
