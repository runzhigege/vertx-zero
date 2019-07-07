package io.zero.epic;

import io.zero.epic.fn.Fn;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

public class Rsa {
    private Rsa() {}

    /**
     * rsa encript for input string.
     *
     * @param strText input string that will be encoded
     * @return The encoded string with rsa
     */
    static String encrypt(final String strText, String keyPath) {
        return Fn.getJvm(() -> {
            RSAPublicKey publicKey = loadRSAPublicKeyByFile(keyPath);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(cipher.doFinal(strText.getBytes()));
        }, strText);

    }

    private static RSAPublicKey loadRSAPublicKeyByFile(String keyPath)
            throws Exception {
        // 1. loading Public Key string by given path
        String publicKeyStr = IO.getString(keyPath);
        //2. generate Public Key Object
        byte[] buffer = Base64.decodeBase64(publicKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }
}
