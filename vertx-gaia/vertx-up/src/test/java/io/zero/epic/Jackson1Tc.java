package io.zero.epic;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.aiki.Ux;
import io.zero.quiz.StoreBase;
import org.junit.Test;

import java.util.Date;
import java.util.TimeZone;

public class Jackson1Tc extends StoreBase {

    @Test
    public void testLocalTime(final TestContext context) {
        final JsonObject data = IO.getJObject(this.getFile("pojo.json"));
        final PojoEntity entity = Ux.fromJson(data, PojoEntity.class);
        final Date endDate = Ut.parse(data.getString("end"));
        final Date startDate = Ut.parse(data.getString("start"));
        System.out.println(TimeZone.getDefault());
        System.out.println("---- Actual ------------");
        System.out.println(data.getString("end"));
        System.out.println(entity.getEnd());
        System.out.println("---- Expected ----------");
        System.out.println(endDate);
        System.out.println(Ut.toDateTime(endDate));
        System.out.println("---- Actual ------------");
        System.out.println(data.getString("start"));
        System.out.println(entity.getStart());
        System.out.println("---- Expected ----------");
        System.out.println(startDate);
        System.out.println(Ut.toDateTime(startDate));
    }
}
