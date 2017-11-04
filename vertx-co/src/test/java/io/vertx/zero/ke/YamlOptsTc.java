package io.vertx.zero.ke;

import com.vie.hors.ZeroException;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.hors.zero.LimeFileException;
import org.junit.Test;
import top.UnitBase;

public class YamlOptsTc extends UnitBase {

    @Test(expected = LimeFileException.class)
    public void testYaml() throws ZeroException {
        final Opts<JsonObject> opts = Opts.get();
        final JsonObject errors = opts.ingest("erro");
        System.out.println(errors);
    }
}
