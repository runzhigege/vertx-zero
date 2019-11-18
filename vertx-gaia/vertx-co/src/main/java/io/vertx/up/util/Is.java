package io.vertx.up.util;

import io.vertx.core.json.JsonObject;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import java.util.Set;

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
                             final Set<String> ignores, final TemporalUnit unit) {
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
        /*
         * Get the final result of calculation.
         */
        final boolean unchanged = oldCopy.fieldNames().stream().allMatch(field -> {
            /*
             * Extract value from each record
             */
            final Object oldValue = oldCopy.getValue(field);
            final Object newValue = newCopy.getValue(field);
            if (Objects.isNull(oldValue) && Objects.isNull(newValue)) {
                /*
                 * ( Unchanged ) When `new` and `old` are both null
                 */
                return true;
            } else if (Objects.nonNull(oldValue) && Objects.nonNull(newValue)) {
                if (Types.isDate(oldValue)) {
                    /*
                     * For `Date` type of `Instant`, there provide comparing method
                     * for different unit kind fo comparing.
                     * 1) Convert to instant first
                     * 2) When `unit` is null, do not comparing other kind of here.
                     */
                    Instant oldDate = Period.parseFull(oldValue.toString())
                            .toInstant();
                    Instant newDate = Period.parseFull(newValue.toString())
                            .toInstant();
                    if (Objects.nonNull(oldDate) && Objects.nonNull(newDate)) {
                        /*
                         * Unit convert here when input `unit` here
                         */
                        if (Objects.nonNull(unit)) {
                            newDate = newDate.truncatedTo(unit);
                            oldDate = oldDate.truncatedTo(unit);
                        }
                        return oldDate.equals(newDate);
                    } else {
                        /*
                         * When the value could not be converted to `Instant`
                         * They are compared with `equals` instead.
                         */
                        return oldValue.equals(newValue);
                    }
                } else {
                    /*
                     * Non date type value here
                     * Compare with `equals`
                     */
                    return oldValue.equals(newValue);
                }
            } else {
                /*
                 * ( Changed ) One is null, another one is not null
                 * They are false
                 */
                return false;
            }
        });
        return !unchanged;
    }
}