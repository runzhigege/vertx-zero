package io.vertx.up.tool;

import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Strings;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Period for datetime processing based on Java8
 */
class Period {

    private static final List<DateTimeFormatter> DATETIMES = new ArrayList<DateTimeFormatter>() {
        {
            this.add(Iso.DATE_TIME);
            this.add(Iso.INSTANT);
            this.add(Iso.RFC1123_DATE_TIME);
            this.add(Iso.COMMON);
            this.add(Iso.READBALE);
        }
    };

    private static final List<DateTimeFormatter> DATES = new ArrayList<DateTimeFormatter>() {
        {
            this.add(Iso.DATE);
            this.add(Iso.BASIC_DATE);
            this.add(Iso.ORDINAL_DATE);
        }
    };

    private static final List<DateTimeFormatter> TIMES = new ArrayList<DateTimeFormatter>() {
        {
            this.add(Iso.TIME);
        }
    };

    private Period() {
    }

    /**
     * Convert to datetime
     *
     * @param literal
     * @return
     */
    static LocalDateTime toDateTime(final String literal) {
        final Optional<DateTimeFormatter> hit =
                Fn.get(Optional.empty(),
                        () -> DATETIMES.stream()
                                .filter(formatter ->
                                        null != Fn.getJvm(
                                                null,
                                                () -> LocalDateTime.parse(literal, formatter),
                                                literal))
                                .findAny(), literal);
        return hit.isPresent() ? LocalDateTime.parse(literal, hit.get()) : null;
    }

    /**
     * Convert to date
     *
     * @param date
     * @return
     */
    static LocalDateTime toDateTime(final Date date) {
        return toDateTime(date.toInstant());
    }


    static LocalDateTime toDateTime(final Instant instant) {
        final ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * Convert to date
     *
     * @param literal
     * @return
     */
    static LocalDate toDate(final String literal) {
        final Optional<DateTimeFormatter> hit =
                Fn.get(Optional.empty(),
                        () -> DATES.stream()
                                .filter(formatter ->
                                        null != Fn.getJvm(
                                                null,
                                                () -> LocalDate.parse(literal, formatter),
                                                literal))
                                .findFirst(), literal);
        return hit.isPresent() ? LocalDate.parse(literal, hit.get()) : null;
    }

    /**
     * Convert to date
     *
     * @param date
     * @return
     */
    static LocalDate toDate(final Date date) {
        final LocalDateTime datetime = toDateTime(date);
        return datetime.toLocalDate();
    }

    static LocalDate toDate(final Instant instant) {
        final LocalDateTime datetime = toDateTime(instant);
        return datetime.toLocalDate();
    }

    /**
     * Convert to time
     *
     * @param literal
     * @return
     */
    static LocalTime toTime(final String literal) {
        final Optional<DateTimeFormatter> hit =
                Fn.get(Optional.empty(),
                        () -> TIMES.stream()
                                .filter(formatter ->
                                        null != Fn.getJvm(
                                                null,
                                                () -> LocalTime.parse(literal, formatter),
                                                literal))
                                .findFirst(), literal);
        return hit.isPresent() ? LocalTime.parse(literal, hit.get()) : null;
    }

    /**
     * Convert to date
     *
     * @param date
     * @return
     */
    static LocalTime toTime(final Date date) {
        final LocalDateTime datetime = toDateTime(date);
        return datetime.toLocalTime();
    }

    static LocalTime toTime(final Instant instant) {
        final LocalDateTime datetime = toDateTime(instant);
        return datetime.toLocalTime();
    }

    /**
     * Check whether it's valid
     *
     * @param literal
     * @return
     */
    static boolean isValid(final String literal) {
        final Date parsed = parse(literal);
        return null != parsed;
    }

    static Date parse(final String literal) {
        return Fn.get(null, () -> {
            final int length = literal.length();
            final String pattern = Storage.PATTERNS_MAP.get(length);
            if (null != pattern) {
                final DateTimeFormatter formatter;
                if (19 == pattern.length()) {
                    // 2018-07-29T16:26:49格式的特殊处理
                    if (0 < literal.indexOf(Strings.T_CHAR)) {
                        formatter = DateTimeFormatter.ofPattern(Storage.T_FORMAT, Locale.getDefault());
                    } else {
                        formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault());
                    }
                } else {
                    formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault());
                }
                final Date converted;
                if (10 == pattern.length()) {
                    final LocalDate date = LocalDate.parse(literal, formatter);
                    converted = parse(date);
                } else if (15 > pattern.length()) {
                    final LocalTime time = LocalTime.parse(literal, formatter);
                    converted = parse(time);
                } else {
                    final LocalDateTime datetime = LocalDateTime.parse(literal, formatter);
                    converted = parse(datetime);
                }
                return converted;
            } else {
                return parseFull(literal);
            }
        }, literal);
    }

    /**
     * Not recommend directly for deep parsing
     *
     * @param literal
     * @return
     */
    static Date parseFull(final String literal) {
        return Fn.get(null, () -> {
            // Datetime parsing
            final LocalDateTime datetime = toDateTime(literal);
            return Fn.nullFlow(datetime,
                    (ref) -> Date.from(ref.atZone(ZoneId.systemDefault()).toInstant()),
                    () -> {
                        // Date parsing
                        final LocalDate date = toDate(literal);
                        return Fn.nullFlow(date,
                                (ref) -> Date.from(ref.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                () -> {
                                    // Time parsing
                                    final LocalTime time = toTime(literal);
                                    return null == time ? null : parse(time);
                                });
                    });
        }, literal);
    }

    static List<String> valueDurationDays(final String from, final String to) {
        final List<String> result = new ArrayList<String>();
        LocalDate begin = LocalDate.parse(from);
        result.add(begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)));
        final LocalDate end = LocalDate.parse(to);
        while (end.isAfter(begin)) {
            begin = begin.plusDays(1);
            result.add(begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)));
        }
        return result;
    }

    static boolean equalDate(final Date left, final Date right) {
        // Compare year
        int leftVal = toItem(left, Calendar.YEAR);
        int rightVal = toItem(right, Calendar.YEAR);
        if (leftVal == rightVal) {
            // Compare month
            leftVal = toItem(left, Calendar.MONTH);
            rightVal = toItem(right, Calendar.MONTH);
            if (leftVal == rightVal) {
                // Compare day
                leftVal = toItem(left, Calendar.DAY_OF_MONTH);
                rightVal = toItem(right, Calendar.DAY_OF_MONTH);
                return leftVal == rightVal;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    static int toMonth(final String literal) {
        final Date date = parse(literal);
        return toItem(date, Calendar.MONTH);
    }

    static int toMonth(final Date date) {
        return toItem(date, Calendar.MONTH);
    }

    static int toYear(final Date date) {
        return toItem(date, Calendar.YEAR);
    }

    static int toYear(final String literal) {
        final Date date = parse(literal);
        return toItem(date, Calendar.YEAR);
    }

    private static int toItem(final Date date, final int flag) {
        final Calendar issue = Calendar.getInstance();
        issue.setTime(date);
        return issue.get(flag);
    }

    static Date parse(final LocalTime time) {
        final LocalDate date = LocalDate.now();
        final LocalDateTime datetime = LocalDateTime.of(date, time);
        return Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
    }

    static Date parse(final LocalDateTime datetime) {
        return Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
    }

    static Date parse(final LocalDate datetime) {
        return Date.from(datetime.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
