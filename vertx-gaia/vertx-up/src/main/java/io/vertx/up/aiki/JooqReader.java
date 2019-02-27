package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jooq.Condition;
import org.jooq.Operator;

import java.util.Arrays;
import java.util.List;

/**
 * Jooq Splitted Reader
 * SELECT
 * COUNT
 * - Search, Check, Find
 */
@SuppressWarnings("all")
public class JooqReader {

    private static JooqReader INSTANCE;
    private transient final VertxDAO vertxDAO;
    private transient JooqAnalyzer analyzer;

    private JooqReader(final VertxDAO vertxDAO) {
        this.vertxDAO = vertxDAO;
    }

    static JooqReader create(final VertxDAO vertxDAO) {
        synchronized (JooqWriter.class) {
            if (null == INSTANCE) {
                INSTANCE = new JooqReader(vertxDAO);
            }
            return INSTANCE;
        }
    }

    JooqReader on(final JooqAnalyzer analyzer) {
        this.analyzer = analyzer;
        return this;
    }

    // ============ Fetch One Operation =============
    /* Async fetch one operation: SELECT */
    <T> Future<T> fetchOneAsync(final String field, final Object value) {
        return Async.toFuture(this.vertxDAO.fetchOneAsync(this.analyzer.getColumn(field), value));
    }

    <T> Future<T> fetchOneAndAsync(final JsonObject filters) {
        final Condition condition = JooqCond.transform(filters, Operator.AND, this.analyzer::getColumn);
        return Async.toFuture(this.vertxDAO.fetchOneAsync(condition));
    }

    <T> Future<T> findByIdAsync(final Object id) {
        return Async.toFuture(this.vertxDAO.findByIdAsync(id));
    }

    <T> Future<List<T>> findAllAsync() {
        return Async.toFuture(this.vertxDAO.findAllAsync());
    }

    /* Sync fetch one operation: SELECT */
    <T> T fetchOne(final String field, final Object value) {
        return toResult(this.vertxDAO.fetchOne(this.analyzer.getColumn(field), value));
    }

    <T> T findById(final Object id) {
        return toResult(this.vertxDAO.findById(id));
    }

    <T> List<T> findAll() {
        return this.vertxDAO.findAll();
    }

    // ============ Exist Operation =============
    Future<Boolean> existsByIdAsync(final Object id) {
        return Async.toFuture(this.vertxDAO.existsByIdAsync(id));
    }

    Boolean existsById(final Object id) {
        return this.vertxDAO.existsById(id);
    }

    // ============ Fetch List with Condition ===========
    <T> Future<List<T>> fetchAsync(final String field, final Object value) {
        return Async.toFuture(this.vertxDAO.fetchAsync(this.analyzer.getColumn(field), Arrays.asList(value)));
    }

    <T> Future<List<T>> fetchAsync(final Condition condition) {
        return Async.toFuture(this.vertxDAO.fetchAsync(condition));
    }

    <T> Future<List<T>> fetchInAsync(final String field, final List<Object> values) {
        return Async.toFuture(this.vertxDAO.fetchAsync(this.analyzer.getColumn(field), values));
    }

    <T> List<T> fetch(final String field, final Object value) {
        return this.vertxDAO.fetch(this.analyzer.getColumn(field), value);
    }

    <T> List<T> fetchIn(final String field, final List<Object> values) {
        return this.vertxDAO.fetch(this.analyzer.getColumn(field), values.toArray());
    }

    // ============ Result Wrapper ==============
    private <T> T toResult(final Object value) {
        return null == value ? null : (T) value;
    }


}
