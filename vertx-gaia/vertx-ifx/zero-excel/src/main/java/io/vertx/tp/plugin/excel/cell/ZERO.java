package io.vertx.tp.plugin.excel.cell;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {

    ConcurrentMap<String, ExValue> VALUE_MAP =
            new ConcurrentHashMap<String, ExValue>() {
                {
                    this.put(Literal.UUID, new UuidValue());
                }
            };
}

interface Literal {
    String UUID = "{UUID}";
}
