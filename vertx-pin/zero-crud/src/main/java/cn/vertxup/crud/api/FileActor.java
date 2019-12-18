package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.cv.IxMsg;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.commune.Envelop;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
        final Promise<Envelop> promise = Promise.promise();
        final File file = new File(filename);
        if (file.exists()) {
            Fn.safeJvm(() -> {
                final InputStream inputStream = new FileInputStream(file);
                this.client.importTable(config.getTable(), inputStream, handler -> {
                    Ix.infoDao(LOGGER, IxMsg.FILE_LOADED, filename);
                    promise.complete(Envelop.success(Boolean.TRUE));
                });
            });
        } else {
            promise.complete(Envelop.success(Boolean.FALSE));
        }
        return promise.future();
    }

    @Address(Addr.File.EXPORT)
    public Future<Envelop> exportFile(final Envelop request) {
        /* Headers */
        final ConcurrentMap<String, String> exportedHeaders = new ConcurrentHashMap<>();
        /* Removed */
        final JsonArray removed = new JsonArray();
        /* Search full column and it will be used in another method */
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> Unity.fetchFull(dao, request, config)

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
                    return Ux.future(columnSet);
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
                    return Ux.future(Ut.toJArray(columnSet));
                })

                /* Projection calculation */
                .compose(projection -> {
                    {
                        /* Removed will be used in future */
                        removed.addAll(projection);
                    }
                    /* Parameters Extraction */
                    final JsonObject body = new JsonObject();
                    final JsonObject criteria = Ux.getJson1(request);
                    body.put(Inquiry.KEY_CRITERIA, criteria);
                    body.put(Inquiry.KEY_PROJECTION, projection);
                    /* Calculation for projection here */
                    return Ux.future(body);
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
                    return Ke.combineAsync(dataArray, exportedHeaders);
                })

                /* Final exporting her for excel download */
                .compose(data -> {
                    final String actor = Ux.getString(request);
                    return this.client.exportTable(actor, data);
                })
                .compose(buffer -> Ux.future(Envelop.success(buffer)))
        );
    }
}
