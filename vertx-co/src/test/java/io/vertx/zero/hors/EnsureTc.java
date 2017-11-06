package io.vertx.zero.hors;

import com.vie.hors.DemonException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.zero.hors.ensure.RequiredFieldException;
import org.junit.Test;
import top.UnitBase;

public class EnsureTc extends UnitBase {

    @Test
    public void testRequired(final TestContext context) {
        final DemonException error =
                new RequiredFieldException(getClass(), new JsonObject(), "name");
        
    }
}
