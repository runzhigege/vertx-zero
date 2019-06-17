package io.vertx.up.aiki;

import io.vertx.core.json.JsonObject;
import io.zero.quiz.JooqBase;
import org.jooq.Condition;
import org.junit.Assert;
import org.junit.Test;

public class JooqCondTc extends JooqBase {

    @Test
    public void testExistsOneAsync() {
        final Condition condition = this.condAnd("existsOneAsync.json");
        // Expected
        final Condition expected = this.eq("name", "Lang")
                .and(this.eq("code", "Test"));
        Assert.assertEquals(condition, expected);
    }


    @Test
    public void testExistsOneAsync2() {
        final Condition condition = this.condAnd("existsOneAsync2.json");
        // Expected
        final Condition expected = this.eq("name", "Lang")
                .and(this.eq("code", "Test"));
        Assert.assertEquals(condition, expected);
    }

    @Test
    public void testFetchOneAndAsync() {
        final Condition condition = this.condAnd("fetchOneAnyAsync.json");
        // Expected
        final Condition expected = this.eq("name", "Lang")
                .and(this.eq("code", "Test"));
        Assert.assertEquals(condition, expected);
    }

    @Test
    public void testParse() {
        final JsonObject filters = this.ioJObject("double.json");
        UxJooq.transform(filters, null);
        UxJooq.transform(filters, null);
    }
}
