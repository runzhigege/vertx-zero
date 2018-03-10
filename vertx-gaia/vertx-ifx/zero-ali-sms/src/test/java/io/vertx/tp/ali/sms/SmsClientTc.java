package io.vertx.tp.ali.sms;

import io.vertx.core.json.JsonObject;
import io.vertx.quiz.ext.web.WebTestBase;
import org.junit.Test;

public class SmsClientTc extends WebTestBase {
    @Test
    public void testMessage() {
        final SmsClient client = SmsClient.createShared(this.vertx);
        client.send("15922611447", "SMS_126585356", new JsonObject().put("code", "387456"), handler -> {
            if (handler.succeeded()) {
                System.out.println(handler.result());
            } else {
                handler.cause().printStackTrace();
            }
        });
    }
}
