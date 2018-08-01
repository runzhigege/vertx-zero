package io.vertx.up.aiki;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.query.Inquiry;
import io.zero.quiz.TestBase;
import org.jooq.Condition;
import org.jooq.Operator;
import org.junit.Test;

public class UxJooqTc extends TestBase {

    @Test
    public void testTransform() {
        final JsonObject input = this.getJson("condition.json");
        final Inquiry inquiry = Inquiry.create(input);
        final Condition condition = UxJooq.transform(inquiry.getCriteria().toJson(), Operator.AND);
    }
}
