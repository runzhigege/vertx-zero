package io.vertx.up.epic;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class PeriodTc {

    @Test
    public void valueDurationDays() {
        final List<String> result = Period.valueDurationDays(Ut.toDateTime("2018-04-02T06:49:41.661Z").toLocalDate().toString(), Ut.toDateTime("2018-05-01T06:49:39.096Z").toLocalDate().toString());
        System.out.println(result);
        System.out.println(Ut.toDate(result.get(0)));
    }

    @Test
    public void valueTest() {
        final Date date = Ut.parse("2018-07-29T16:26:49");
        System.out.println(date);
    }
}
