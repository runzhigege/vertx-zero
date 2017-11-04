package top;

import com.vie.util.Log;
import com.vie.util.Store;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public abstract class StoreBase {

    @Rule
    public final RunTestOnContext rule = new RunTestOnContext();

    protected String getFile(final String filename) {
        final Class<?> clazz = getClass();
        final Logger LOGGER
                = LoggerFactory.getLogger(clazz);
        final String file = "test/" + clazz.getPackage().getName() + "/" + filename;
        Log.info(LOGGER, "[TEST] Test input file: {0}", file);
        return file;
    }

    protected void execJObject(final String filename,
                               final Handler<AsyncResult<JsonObject>> handler) {
        final ConfigStoreOptions options = Store.getJson(getFile(filename));
        final ConfigRetrieverOptions retrieverOptions = new ConfigRetrieverOptions().addStore(options);
        final Vertx vertx = this.rule.vertx();
        final ConfigRetriever retriever = ConfigRetriever.create(vertx, retrieverOptions);
        retriever.getConfig(handler);
    }
}
