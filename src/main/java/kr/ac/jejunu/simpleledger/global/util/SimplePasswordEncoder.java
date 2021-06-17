package kr.ac.jejunu.simpleledger.global.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimplePasswordEncoder {

    public static String encode(String rawPassword) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(rawPassword.getBytes(StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        for (byte b: messageDigest.digest()) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
