package io.vertx.up.aiki;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.zero.epic.Ut;
import io.vertx.quiz.StoreBase;
import org.junit.Test;

import java.time.LocalDateTime;

public class UxToTc extends StoreBase {

    @Test
    public void testUserJson(final TestContext context) {
        final JsonObject data = Ux.toJson(this.getEntity());
        this.getLogger().info("[ ZERO TEST ] Data: {0}", data);
        context.assertEquals(this.getEntityJson(), data);
    }

    @Test
    public void testUserPojo(final TestContext context) {
        final JsonObject data = Ux.toJson(this.getEntity(), "user");
        this.getLogger().info("[ ZERO TEST ] Pojo Data: {0}", data);
        context.assertEquals(this.getEntityToJson(), data);
    }

    @Test
    public void testUserSerial() {
        final DateTimeJson json = new DateTimeJson();
        System.out.println(Ut.serialize(json));
    }

    private UserJson getEntity() {
        final UserJson json = new UserJson();
        json.setAge(12);
        json.setEmail("lang.yu@hpe.com");
        json.setName("Lang");
        return json;
    }

    private JsonObject getEntityJson() {
        return new JsonObject()
                .put("age", 12)
                .put("email", "lang.yu@hpe.com")
                .put("name", "Lang");
    }

    private JsonObject getEntityToJson() {
        return new JsonObject()
                .put("age", 12)
                .put("zEmail", "lang.yu@hpe.com")
                .put("zName", "Lang");
    }
}

class DateTimeJson {
    private LocalDateTime time = LocalDateTime.now();

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(final LocalDateTime time) {
        this.time = time;
    }
}
