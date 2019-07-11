package io.vertx.zero.epic;

import io.vertx.zero.eon.Values;
import io.vertx.zero.epic.fn.Fn;

import java.security.MessageDigest;

final class Codec {
    private Codec() {
    }

    /**
     * MD5 encript for input string.
     *
     * @param input input string that will be encoded
     * @return The encoded string with MD5
     */
    static String md5(final String input) {
        return Fn.getJvm(() -> {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            final byte[] source = input.getBytes(Values.CHARSET);
            digest.update(source);
            final byte[] middle = digest.digest();
            final char[] middleStr = new char[16 * 2];
            int position = 0;
            for (int idx = 0; idx < 16; idx++) {
                final byte byte0 = middle[idx];
                middleStr[position++] = Values.HEX_ARR[byte0 >>> 4 & 0xF];
                middleStr[position++] = Values.HEX_ARR[byte0 & 0xF];
            }
            return new String(middleStr);
        }, input);
    }

    /**
     * SHA-256
     *
     * @param input input string that will be encoded
     * @return The encoded string with sha256
     */
    static String sha256(final String input) {
        return sha(input, "SHA-256");
    }

    /**
     * SHA-512
     *
     * @param input input string that will be encoded
     * @return The encoded string with sha512
     */
    static String sha512(final String input) {
        return sha(input, "SHA-512");
    }

    @SuppressWarnings("all")
    private static String sha(final String strText, final String strType) {
        return Fn.getJvm(() -> {
            final MessageDigest messageDigest = MessageDigest.getInstance(strType);
            messageDigest.update(strText.getBytes());
            final byte[] byteBuffer = messageDigest.digest();
            final StringBuilder strHexString = new StringBuilder();
            for (int i = 0; i < byteBuffer.length; i++) {
                final String hex = Integer.toHexString(0xff & byteBuffer[i]);
                if (hex.length() == 1) {
                    strHexString.append('0');
                }
                strHexString.append(hex);
            }
            return strHexString.toString();
        }, strText, strType);
    }
}
