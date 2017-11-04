package top;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public abstract class UnitBase {
    @Rule
    public final RunTestOnContext rule = new RunTestOnContext();

    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }
}
