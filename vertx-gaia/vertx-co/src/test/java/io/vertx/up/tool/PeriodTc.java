package io.vertx.up.tool;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PeriodTc {

    @Test
    public void valueDurationDays() {
        List<String> result = Period.valueDurationDays(Ut.toDateTime("2018-04-02T06:49:41.661Z").toLocalDate().toString(),Ut.toDateTime("2018-05-01T06:49:39.096Z").toLocalDate().toString());
        System.out.println(result);
        System.out.println(Ut.toDate(result.get(0)));
    }
}
