package io.vertx.tp.plugin.excel;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.excel.atom.ExRecord;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ExcelClientImpl implements ExcelClient {

    private static final Annal LOGGER = Annal.get(ExcelClientImpl.class);

    private transient final Vertx vertx;
    private transient final ExcelHelper helper = ExcelHelper.helper(this.getClass());

    ExcelClientImpl(final Vertx vertx, final JsonObject config) {
        this.vertx = vertx;
        this.init(config);
    }

    @Override
    public ExcelClient init(final JsonObject config) {
        final JsonArray mapping = config.getJsonArray(MAPPING);
        this.helper.initConnect(mapping);
        LOGGER.info("[ Excel ] Configuration finished: {0}", Pool.CONNECTS.size());
        return this;
    }

    @Override
    public ExcelClient ingestList(final String filename, final Handler<AsyncResult<Set<ExTable>>> handler) {
        /* 1. Get Workbook reference */
        final Workbook workbook = this.helper.getWorkbook(filename);
        /* 2. Iterator for Sheet */
        final Set<ExTable> tables = this.helper.getExTables(workbook);
        handler.handle(Future.succeededFuture(tables));
        return this;
    }

    @Override
    public ExcelClient ingestList(final InputStream in, final boolean isXlsx, final Handler<AsyncResult<Set<ExTable>>> handler) {
        /* 1. Get Workbook reference */
        final Workbook workbook = this.helper.getWorkbook(in, isXlsx);
        /* 2. Iterator for Sheet */
        final Set<ExTable> tables = this.helper.getExTables(workbook);
        handler.handle(Future.succeededFuture(tables));
        return this;
    }

    @Override
    public ExcelClient ingest(final String filename, final Handler<AsyncResult<ExTable>> handler) {
        return this.ingestList(filename, processed -> {
            if (processed.succeeded()) {
                processed.result().forEach(table ->
                        handler.handle(Future.succeededFuture(table)));
            }
        });
    }

    @Override
    public <T> ExcelClient loading(final String filename, final Handler<AsyncResult<Set<T>>> handler) {
        return this.ingestList(filename, process -> handler.handle(this.handleIngested(process)));
    }

    @Override
    public <T> ExcelClient loading(final InputStream in, final boolean isXlsx, final Handler<AsyncResult<Set<T>>> handler) {
        return this.ingestList(in, isXlsx, process -> handler.handle(this.handleIngested(process)));
    }

    private <T> Future<Set<T>> handleIngested(final AsyncResult<Set<ExTable>> async) {
        if (async.succeeded()) {
            final Set<ExTable> tables = async.result();
            /* 3. Loading data into the system */
            final Set<T> entitySet = new HashSet<>();
            tables.forEach(table -> this.extract(table).forEach(json -> {
                /* 4. Filters building */
                final JsonObject filters = table.getFilters(json);
                final T entity = this.saveEntity(filters, json, table);
                if (Objects.nonNull(entity)) {
                    entitySet.add(entity);
                }
            }));
            /* 4. Set<T> handler */
            return Future.succeededFuture(entitySet);
        } else {
            return Future.succeededFuture();
        }
    }

    private <T> T saveEntity(final JsonObject filters, final JsonObject data, final ExTable table) {
        T reference = null;
        if (Objects.nonNull(table.getPojo()) && Objects.nonNull(table.getDao())) {
            LOGGER.info("[ Excel ] Filters: {0}, Table: {1}", filters.encode(), table.getName());
            final T entity = Ut.deserialize(data, table.getPojo());
            final UxJooq jooq = Ux.Jooq.on(table.getDao());
            if (null != jooq) {
                final T queried = jooq.fetchOne(filters);
                if (null == queried) {
                    reference = jooq.insert(entity);
                } else {
                    reference = jooq.update(entity);
                }
            }
        }
        return reference;
    }

    private List<JsonObject> extract(final ExTable table) {
        /* Records extracting */
        final List<ExRecord> records = table.get();
        LOGGER.info("[ Excel ] Table: {0}, Data Size: {1}", table.getName(), records.size());
        /* Pojo Processing */
        return records.stream().filter(Objects::nonNull)
                .map(ExRecord::toJson)
                .collect(Collectors.toList());
    }
}
