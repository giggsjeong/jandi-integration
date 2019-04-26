package com.jandi.integration.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.SecureRandom;

public class AuthKeyGenerator {

    public static String generateAccessKey() {
        return generateKey(20);
    }

    public static String generateSecretAccessId() {
        return generateKey(30);
    }

    private static String generateKey(int length) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[length];
        random.nextBytes(bytes);
        return Base64.encodeBase64String(bytes);
    }
}
