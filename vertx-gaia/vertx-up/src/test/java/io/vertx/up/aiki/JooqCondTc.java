package io.vertx.up.aiki;

import io.vertx.zero.exception.JooqModeConflictException;
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

    @Test(expected = JooqModeConflictException.class)
    public void testExistsOneAsync1() {
        final Condition condition = this.condAnd("existsOneAsync1.json");
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

    @Test(expected = JooqModeConflictException.class)
    public void testFetchOneAndAsync1() {
        final Condition condition = this.condAnd("fetchOneAnyAsync1.json");
        // Expected
        final Condition expected = this.eq("name", "Lang")
                .and(this.eq("code", "Test"));
        Assert.assertEquals(condition, expected);
    }

    @Test
    public void testFetchOneAndAsync2() {
        final Condition condition = this.condAnd("fetchOneAnyAsync2.json");
        // Expected
        final Condition expected = this.eq("name", "Lang")
                .and(this.eq("code", "Test"));
        Assert.assertEquals(condition, expected);
    }
}
