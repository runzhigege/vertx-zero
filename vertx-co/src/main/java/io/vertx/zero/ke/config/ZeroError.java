package io.vertx.zero.ke.config;

import com.vie.hoc.HPool;
import com.vie.util.Instance;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.ZeroNode;

/**
 * @author lang
 */
public class ZeroError implements ZeroNode<JsonObject> {

    private static final String KEY = "error";

    private transient final ZeroNode<JsonObject> node
            = HPool.exec(Storage.NODES, KEY,
            () -> Instance.instance(ZeroPlugin.class, KEY));

    @Override
    public JsonObject read() {
        return this.node.read();
    }
}
