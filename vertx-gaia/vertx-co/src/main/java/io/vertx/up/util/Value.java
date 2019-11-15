package io.vertx.up.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

class Value {
    /*
     * Json valid value here
     */
    static Object toJValue(final Object input) {
        if (Objects.isNull(input)) {
            return null;
        } else {
            if (input instanceof Date) {
                return ((Date) input).toInstant();
            } else {
                if ("null".equals(input)) {
                    return null;
                } else {
                    final Class<?> type = input.getClass();
                    if (LocalDateTime.class == type) {
                        return Ut.parse((LocalDateTime) input).toInstant();
                    } else if (LocalDate.class == type) {
                        return Ut.parse((LocalDate) input).toInstant();
                    } else if (LocalTime.class == type) {
                        return Ut.parse((LocalTime) input).toInstant();
                    } else {
                        return input;
                    }
                }
            }
        }
    }

    static Object toValue(final Object value, final Class<?> type) {
        if (Objects.isNull(value)) {
            return null;
        } else {
            if (Objects.isNull(type)) {
                return value.toString();
            } else {
                Object normalized = null;
                if (LocalTime.class == type) {
                    final Date date = Ut.parseFull(value.toString());
                    normalized = Ut.toTime(date);
                } else if (LocalDate.class == type) {
                    final Date date = Ut.parseFull(value.toString());
                    normalized = Ut.toDate(date);
                } else if (LocalDateTime.class == type) {
                    final Date date = Ut.parseFull(value.toString());
                    normalized = Ut.toDateTime(date);
                }
                return normalized;
            }
        }
    }
}
