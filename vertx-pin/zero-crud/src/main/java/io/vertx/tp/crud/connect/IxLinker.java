package io.vertx.tp.crud.connect;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.up.commune.Envelop;
import io.vertx.up.fn.Fn;

/*
 * Configuration for linker `connect` configuration
 */
public interface IxLinker {
    static IxLinker create() {
        return Fn.pool(Pool.LINKER_MAP, CreateLinker.class.getName(),
                CreateLinker::new);
    }

    Future<Envelop> procAsync(Envelop data, JsonObject original, IxModule module);
}
