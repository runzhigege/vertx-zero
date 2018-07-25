package io.vertx.up.epic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class PeriodTc {

    private void assertParse(final String left, final String right) {
        final Date time = Ut.parse(left);
        final Date time1 = Ut.parse(right);
        Assert.assertEquals(time, time1);
    }

    @Test
    public void testParse() {
        this.assertParse("2018-07-25 16:40:56", "2018-07-25T08:40:56Z");
    }

    @Test
    public void testParse1() {
        this.assertParse("2018-07-25 16:40:56.776", "2018-07-25T16:40:56.776");
    }

    @Test
    public void testParse2() {
        this.assertParse("2018-07-25 16:40:56.776", "2018-07-25T08:40:56.776Z");
    }

    @Test
    public void testParse3() {
        this.assertParse("2018-07-25", "2018-07-25");
    }
}
