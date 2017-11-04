package com.vie.hoc;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public class HPool {
    /**
     * Execute pool instance
     *
     * @param pool
     * @param key
     * @param poolFn
     * @param <T>
     * @return
     */
    public static <T> T exec(final ConcurrentMap<String, T> pool,
                             final String key,
                             final Supplier<T> poolFn) {
        return HBool.exec(null == pool || null == key,
                () -> null,
                () -> {
                    T reference = pool.get(key);
                    if (null == reference) {
                        reference = poolFn.get();
                        if (null != reference) {
                            pool.put(key, reference);
                        }
                    }
                    return reference;
                });
    }
}
