package io.vertx.zero.ke.reliable;

import com.vie.hors.ZeroException;
import com.vie.util.io.IO;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.hors.ensure.RequiredFieldException;
import io.vertx.zero.ke.reliable.item.RequiredInsurer;
import org.junit.Test;
import top.UnitBase;

public class JsonInsurerTc extends UnitBase {

    @Test(expected = RequiredFieldException.class)
    public void testInsure() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-required.yml"));
        final JsonObject data = IO.getJObject(getFile("rule-required-data.json"));
        ensure(RequiredInsurer.class, data, rule);
    }
}
