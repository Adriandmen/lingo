package com.adrianmensing.security;

import java.security.SecureRandom;

public class TokenGenerator {
    private static final String ALPHA_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERICAL_CHARS = "0123456789";

    private static final String       ALPHABET = ALPHA_UPPERCASE + ALPHA_LOWERCASE + NUMERICAL_CHARS;
    private static final SecureRandom RANDOM   = new SecureRandom();

    public static String generateToken(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Expected size to be an integer larger than 0, but got: " + size);

        StringBuilder builder = new StringBuilder();
        while (builder.length() != size) {
            int index = RANDOM.nextInt(ALPHABET.length());

            builder.append(ALPHABET.charAt(index));
        }

        return builder.toString();
    }
}
