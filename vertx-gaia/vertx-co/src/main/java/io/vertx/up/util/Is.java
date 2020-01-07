package io.vertx.up.util;

import io.vertx.core.json.JsonObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/*
 * Specific checking
 */
class Is {

    /*
     * Whether record contains all the data in cond.
     * JsonObject subset here for checking
     */
    static boolean isSubset(final JsonObject cond, final JsonObject record) {
        final Set<String> fields = cond.fieldNames();
        final long counter = fields.stream()
                /* record contains all cond */
                .filter(record::containsKey)
                .filter(field -> Compare.equal(record.getValue(field), cond.getValue(field)))
                .count();
        return fields.size() == counter;
    }

    static boolean isChanged(final JsonObject oldRecord, final JsonObject newRecord,
                             final Set<String> ignores, final ConcurrentMap<String, Class<?>> dateFields) {
        /*
         * copy each compared json object and remove
         * all fields that will not be compared here.
         */
        final JsonObject oldCopy = oldRecord.copy();
        final JsonObject newCopy = newRecord.copy();
        if (Objects.nonNull(ignores) && !ignores.isEmpty()) {
            ignores.forEach(oldCopy::remove);
            ignores.forEach(newCopy::remove);
        }
        final Set<String> dateFieldSet = Objects.isNull(dateFields) ? new HashSet<>() : dateFields.keySet();
        /*
         * Get the final result of calculation.
         * 1) From old calculation
         */
        final boolean unchanged = oldCopy.fieldNames().stream().allMatch(field -> {
            /*
             * Extract value from each record
             */
            final Object oldValue = oldCopy.getValue(field);
            final Object newValue = newCopy.getValue(field);
            final TemporalUnit unit = getUnit(dateFields.get(field));
            return isSame(oldValue, newValue, dateFieldSet.contains(field), unit);
        });
        /*
         * 2) From new calculation
         */
        final Set<String> newLefts = new HashSet<>(newCopy.fieldNames());
        newLefts.removeAll(oldCopy.fieldNames());
        final boolean additional = newLefts.stream().allMatch(field -> {
            /*
             * Extract value from each record
             */
            final Object oldValue = oldCopy.getValue(field);
            final Object newValue = newCopy.getValue(field);
            final TemporalUnit unit = getUnit(dateFields.get(field));
            return isSame(oldValue, newValue, dateFieldSet.contains(field), unit);
        });
        return !(unchanged && additional);
    }

    static TemporalUnit getUnit(final Class<?> clazz) {
        final TemporalUnit unit;
        if (LocalDateTime.class == clazz || LocalTime.class == clazz) {
            /*
             * 按分钟
             */
            unit = ChronoUnit.MINUTES;
        } else {
            /*
             * 某天
             */
            unit = ChronoUnit.DAYS;
        }
        return unit;
    }

    static boolean isSame(final Object oldValue, final Object newValue,
                          final boolean isDate,
                          final TemporalUnit unit) {

        if (Objects.isNull(oldValue) && Objects.isNull(newValue)) {
            /*
             * ( Unchanged ) When `new` and `old` are both null
             */
            return true;
        } else if (Objects.nonNull(oldValue) && Objects.nonNull(newValue)) {
            if (Types.isDate(oldValue) && isDate) {
                /*
                 * For `Date` type of `Instant`, there provide comparing method
                 * for different unit kind fo comparing.
                 * 1) Convert to instant first
                 * 2) When `unit` is null, do not comparing other kind of here.
                 */
                final Instant oldInstant = Period.parseFull(oldValue.toString())
                        .toInstant();
                final Instant newInstant = Period.parseFull(newValue.toString())
                        .toInstant();
                /*
                 * Compared by unit
                 */
                final LocalDateTime oldDateTime = Period.toDateTime(oldInstant);
                final LocalDateTime newDateTime = Period.toDateTime(newInstant);
                /*
                 * Only compared Date
                 */
                final LocalDate oldDate = oldDateTime.toLocalDate();
                final LocalDate newDate = newDateTime.toLocalDate();

                final LocalTime oldTime = oldDateTime.toLocalTime();
                final LocalTime newTime = newDateTime.toLocalTime();
                if (ChronoUnit.DAYS == unit) {
                    /*
                     * Date Only
                     */
                    return oldDate.isEqual(newDate);
                } else if (ChronoUnit.MINUTES == unit) {
                    /*
                     * Time to HH:mm
                     */
                    return oldDate.isEqual(newDate) &&
                            (oldTime.getHour() == newTime.getHour())
                            && (oldTime.getMinute() == newTime.getMinute());
                } else {
                    /*
                     * DateTime completed
                     */
                    return oldDate.isEqual(newDate) &&
                            oldTime.equals(newTime);
                }
            } else {
                /*
                 * Non date type value here
                 * Compare with `equals`
                 * Except `Date` type, we must set String literal to be compared
                 * instead of data type conversation
                 */
                return oldValue.toString().equals(newValue.toString());
            }
        } else {
            /*
             * ( Changed ) One is null, another one is not null
             * They are false
             */
            return false;
        }
    }
}
