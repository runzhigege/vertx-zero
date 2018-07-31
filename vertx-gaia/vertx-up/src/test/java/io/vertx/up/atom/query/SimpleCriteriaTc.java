package io.vertx.up.atom.query;

import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.UxJooq;
import io.zero.quiz.TestBase;
import org.jooq.Operator;
import org.junit.Test;

public class SimpleCriteriaTc extends TestBase {

    @Test
    public void testSimple() {
        final JsonObject demo = this.getJson("demo1.json");
        final Criteria criteria = Criteria.create(demo.getJsonObject("demo1"));
        System.out.println(criteria.toJson());
        System.out.println(demo.getJsonObject("demo1"));
        UxJooq.transform(criteria.toJson(), Operator.AND);
    }

    @Test
    public void testSimple1() {
        final JsonObject demo = this.getJson("demo1.json");
        final Criteria criteria = Criteria.create(demo.getJsonObject("demo2"));
        System.out.println(criteria.toJson());
        UxJooq.transform(criteria.toJson(), Operator.AND);
    }
}
