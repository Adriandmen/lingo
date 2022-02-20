package com.adrianmensing.security;

import static com.adrianmensing.security.AuthTokenService.TOKEN_LENGTH;

/**
 * A simple way of managing user tokens.
 *
 * <p>no idea what the fuck spring-boot is on about.</p>
 */
class KeyTokenPair {
    public final String secretToken;
    public final String id;

    public KeyTokenPair(String secretToken, String userId) {
        this.secretToken = secretToken;
        this.id = userId;
    }

    public static KeyTokenPair newKeyTokenPair(int userId) {
        String token = TokenGenerator.generateToken(TOKEN_LENGTH);
        String id    = Integer.toString(userId);

        return new KeyTokenPair(token, id);
    }
}