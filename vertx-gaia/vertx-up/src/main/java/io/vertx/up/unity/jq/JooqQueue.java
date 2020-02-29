package io.vertx.up.unity.jq;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.ChangeFlag;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiPredicate;

/**
 * @author lang
 * <p>
 * Batch operation help tools, it could help developer to process database batch operations
 * It's high performance channel for batch operations in groups
 */
@SuppressWarnings("all")
class JooqQueue {

    private transient final VertxDAO vertxDAO;
    private transient JooqReader reader;
    private transient JooqAnalyzer analyzer;
    private transient JooqWriter writer;

    private JooqQueue(final VertxDAO vertxDAO) {
        this.vertxDAO = vertxDAO;
    }

    static JooqQueue create(final VertxDAO vertxDAO) {
        return new JooqQueue(vertxDAO);
    }

    JooqQueue on(final JooqAnalyzer analyzer) {
        this.analyzer = analyzer;
        return this;
    }

    JooqQueue on(final JooqReader reader) {
        this.reader = reader;
        return this;
    }

    JooqQueue on(final JooqWriter writer) {
        this.writer = writer;
        return this;
    }

    // ============ Batch Operations =============
    <T> List<T> upsert(final JsonObject filters, final List<T> list, final BiPredicate<T, T> fnCombine) {
        final List<T> original = this.analyzer.<T>search(filters);
        /*
         * Query data by filters
         */
        final ConcurrentMap<ChangeFlag, List<T>> queueMap = compared(list, original, fnCombine);
        /*
         * Insert & Update
         */
        final List<T> resultList = new ArrayList<>();
        resultList.addAll(this.writer.insert(queueMap.get(ChangeFlag.ADD)));
        resultList.addAll(this.writer.update(queueMap.get(ChangeFlag.UPDATE)));
        return resultList;
    }

    <T> Future<List<T>> upsertAsync(final JsonObject filters, final List<T> list, final BiPredicate<T, T> fnCombine) {
        /*
         * Query data by filters ( This filters should fetch all condition list as List<T> )
         * original
         */
        return this.analyzer.<T>searchAsync(filters).compose(original -> {
            /*
             * Combine original / and last list
             */
            final ConcurrentMap<ChangeFlag, List<T>> queueMap = compared(list, original, fnCombine);
            /*
             * Insert & Update
             */
            final List<Future<List<T>>> futures = new ArrayList<>();
            futures.add(this.writer.insertAsync(queueMap.get(ChangeFlag.ADD)));
            futures.add(this.writer.updateAsync(queueMap.get(ChangeFlag.UPDATE)));
            return Ux.thenCombineArrayT(futures);
        });
    }

    private <T> ConcurrentMap<ChangeFlag, List<T>> compared(final List<T> current, final List<T> original,
                                                            final BiPredicate<T, T> fnCombine) {
        /*
         * Combine original / and last list
         */
        final List<T> addQueue = new ArrayList<>();
        final List<T> updateQueue = new ArrayList<>();
        /*
         * Only get `ADD` & `UPDATE`
         * Iterate original list
         * 1) If the entity is missing in original, ADD
         * 2) If the entity is existing in original, UPDATE
         */
        current.forEach(newRecord -> {
            /*
             * New record found in `original`
             */
            final T found = Ut.elementFind(original, oldRecord -> fnCombine.test(oldRecord, newRecord));
            if (Objects.isNull(found)) {
                addQueue.add(newRecord);
            } else {
                final T combine = this.analyzer.copyEntity(found, newRecord);
                updateQueue.add(combine);
            }
        });
        return new ConcurrentHashMap<ChangeFlag, List<T>>() {
            {
                put(ChangeFlag.ADD, addQueue);
                put(ChangeFlag.UPDATE, updateQueue);
            }
        };
    }
}
