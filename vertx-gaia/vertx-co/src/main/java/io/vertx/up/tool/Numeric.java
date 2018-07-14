package io.vertx.up.tool;

import io.vertx.up.func.Fn;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Number checking
 */
class Numeric {

    static Integer mathMultiply(final Integer left, final Integer right) {
        final Integer leftValue = Fn.get(0, () -> left, left);
        final Integer rightValue = Fn.get(0, () -> right, right);
        return Math.multiplyExact(leftValue, rightValue);
    }

    private static boolean isMatch(final String regex, final String original) {
        return Fn.get(() -> {
            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(original);
            return matcher.matches();
        }, regex, original);
    }

    static boolean isPositive(final String original) {
        return isMatch("^\\+{0,1}[0-9]\\d*", original);
    }

    static boolean isNegative(final String original) {
        return isMatch("^-[0-9]\\d*", original);
    }

    static boolean isInteger(final String original) {
        return isMatch("[+-]{0,1}0", original) || isPositive(original) || isNegative(original);
    }

    static boolean isDecimal(final String original) {
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", original);
    }

    static boolean isReal(final String original) {
        return isInteger(original) || isDecimal(original);
    }

    static Integer randomNumber(final int length) {
        // 1. Generate seed
        final StringBuilder min = new StringBuilder();
        final StringBuilder max = new StringBuilder();
        // 2. Calculate
        min.append(1);
        for (int idx = 0; idx < length; idx++) {
            min.append(0);
            max.append(9);
        }
        // 3. min/max
        final Integer minValue = Integer.parseInt(min.toString()) / 10;
        final Integer maxValue = Integer.parseInt(max.toString());
        final Random random = new Random();
        return minValue + random.nextInt(maxValue - minValue);
    }

    static class Decimal {

        static boolean isPositive(final String original) {
            return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", original);
        }

        static boolean isNegative(final String original) {
            return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", original);
        }
    }
}
