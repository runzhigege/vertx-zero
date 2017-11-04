package io.vertx.zero.ke.config;

import com.vie.hoc.HFail;
import com.vie.util.Instance;
import com.vie.util.io.IO;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.ZeroNode;

import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 */
public class ZeroError implements ZeroNode<JsonObject> {

    private transient final ZeroNode<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    @Override
    public JsonObject read() {
        // 1. Read all map data.
        final ConcurrentMap<String, String> dataMap = this.node.read();
        // 2. Read error configuration
        final String filename = dataMap.get("error");
        // 3. Read new data from extension file
        return HFail.execDft(
                () -> IO.getYaml(filename),
                new JsonObject(), filename);
    }
}
